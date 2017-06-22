package com.weststein.handler.card;

import com.weststein.controller.secured.model.CardHolderModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardInquiry;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetCardHolderHandler {

    @Autowired
    private PPFService<CardInquiry, AccountAPIv2CardInfo> ppfService;
    @Autowired
    private OrikoObjectMapper orikoObjectMapper;

    public CardHolderModel handle(String cardHolderId) {

        AccountAPIv2CardInfo res2 = ppfService.get(CardInquiry.builder().cardholderid(cardHolderId).build(),
                AccountAPIv2CardInfo.class);
        return orikoObjectMapper.map(res2.getCardInquiry().getCardHolder(), CardHolderModel.class);
    }
}
