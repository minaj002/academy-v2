package com.weststein.handler.transaction;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardToCard;
import com.weststein.integration.response.AccountAPIv2CardToCard;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class CardToCardPaymentHandler {

    @Autowired
    private PPFService<CardToCard, AccountAPIv2CardToCard> ppfService;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public AccountAPIv2CardToCard handle(Long id, String cardNumberTo, BigDecimal amount) {

        CardholderId cardholderId = cardholderIdRepository.findOne(id);
        CardToCard depositObject = new CardToCard();
        depositObject.setCardHolderId(cardholderId.getCardholderId());
        depositObject.setCardNumberTo(cardNumberTo);
        depositObject.setCurrencyCode("EUR");
        depositObject.setAmount(amount);

        depositObject.setSettlementCurrencyCode("EUR");
        depositObject.setDescription("Card To Card Transfer WEB");
        depositObject.setTerminalOwner("WestStein");
        depositObject.setTerminalLocation("WestStein");
        depositObject.setTerminalCity("Riga");
        depositObject.setTerminalID("111");
        depositObject.setTerminalState("LV");
        depositObject.setCountry("LV");
        depositObject.setDirectFee("**WTR");
        return ppfService.get(depositObject,
                AccountAPIv2CardToCard.class);
    }
}
