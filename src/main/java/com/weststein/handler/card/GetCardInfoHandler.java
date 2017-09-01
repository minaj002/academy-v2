package com.weststein.handler.card;

import com.weststein.controller.secured.model.CardInfoModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardInquiry;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetCardInfoHandler {

    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private PPFService<CardInquiry, AccountAPIv2CardInfo> ppfService;
    @Autowired
    private OrikoObjectMapper orikoObjectMapper;

    public CardInfoModel handle(Long id) {

        CardholderId cardholderId = cardholderIdRepository.findOne(id);
        AccountAPIv2CardInfo res2 = ppfService.get(CardInquiry.builder().cardholderId(cardholderId.getCardholderId()).build(),
                AccountAPIv2CardInfo.class);
        CardInfoModel cardInfo = orikoObjectMapper.map(res2.getCardInquiry().getCardInfo(), CardInfoModel.class);
        cardInfo.setCardNumber(res2.getCardInquiry().getCardHolder().getCardNumber());
        return cardInfo;
    }
}
