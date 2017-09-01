package com.weststein.handler.transaction;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.DepositToCard;
import com.weststein.integration.response.AccountAPIv2DepositToCard;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class DepositVoucherHandler {

    @Autowired
    private PPFService<DepositToCard, AccountAPIv2DepositToCard> ppfService;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public AccountAPIv2DepositToCard handle(Long id, String voucher) {


        CardholderId cardholderId = cardholderIdRepository.findOne(id);

        DepositToCard depositObject = new DepositToCard();
        depositObject.setVoucherCode(voucher);
        depositObject.setCardHolderId(cardholderId.getCardholderId());
        depositObject.setTransactionType("0");
        depositObject.setCurrencyCode("EUR");
        depositObject.setSettlementAmount(BigDecimal.ZERO);
        depositObject.setSettlementCurrencyCode("EUR");
        depositObject.setTransactionDescription("Deposit To Card API");
        depositObject.setTerminalOwner("WestStein");
        depositObject.setTerminalLocation("WestStein API WEB");
        depositObject.setTerminalCity("Riga");
        depositObject.setCountry("LV");
        depositObject.setDirectFee("**VLO");
        return ppfService.get(depositObject,
                AccountAPIv2DepositToCard.class);
    }
}
