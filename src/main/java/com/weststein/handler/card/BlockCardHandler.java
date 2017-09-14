package com.weststein.handler.card;

import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardInquiry;
import com.weststein.integration.request.ChangeCardStatus;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import com.weststein.integration.response.AccountAPIv2ChangeCardStatus;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class BlockCardHandler {

    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private PPFService<ChangeCardStatus, AccountAPIv2ChangeCardStatus> changeStatusPpfService;
    @Autowired
    private PPFService<CardInquiry, AccountAPIv2CardInfo> getInfoPpfService;

    public void handle(Long id) {
        CardholderId cardholderId = cardholderIdRepository.findOne(id);

        AccountAPIv2CardInfo res2 = getInfoPpfService.get(CardInquiry.builder().cardholderId(cardholderId.getCardholderId()).build(),
                AccountAPIv2CardInfo.class);
        Integer currentStatus = Integer.valueOf(res2.getCardInquiry().getCardInfo().getCardStatus());
        if (currentStatus.equals(Integer.valueOf(0)) || currentStatus.equals(Integer.valueOf(1))) {
            changeStatusPpfService.get(ChangeCardStatus.builder().cardHolderId(cardholderId.getCardholderId())
                    .oldStatus(currentStatus.toString()).newStatus("9").build(), AccountAPIv2ChangeCardStatus.class);

        }
        List<ValidationError> errors = new ArrayList();
        errors.add(ValidationError.builder().field("status").message("current card status is " + currentStatus + ", but must be 0 or 1").build());
        throw new ValidationException(errors, "can not black this card");
    }
}
