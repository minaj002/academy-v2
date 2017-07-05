package com.weststein.handler.transaction;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.DepositToCard;
import com.weststein.integration.response.AccountAPIv2DepositToCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class DepositVoucherHandler {

    @Autowired
    private PPFService<DepositToCard, AccountAPIv2DepositToCard> ppfService;

    public AccountAPIv2DepositToCard handle(String cardholderId, String voucher) {

        DepositToCard depositObject = new DepositToCard();
        depositObject.setVoucherCode(voucher);
        depositObject.setCardHolderId(cardholderId);
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
        AccountAPIv2DepositToCard res2 = ppfService.get(depositObject,
                AccountAPIv2DepositToCard.class);
        return res2;
    }
}
