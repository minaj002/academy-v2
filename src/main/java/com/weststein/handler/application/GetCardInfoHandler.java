package com.weststein.handler.application;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardInquiry;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetCardInfoHandler {

    @Autowired
    private PPFService<CardInquiry, AccountAPIv2CardInfo> ppfService;

    public AccountAPIv2CardInfo handle(String cardHolderId) {


        AccountAPIv2CardInfo res2 = ppfService.get(CardInquiry.builder().cardholderid(cardHolderId).build(),
                AccountAPIv2CardInfo.class);
        return res2;
    }
}
