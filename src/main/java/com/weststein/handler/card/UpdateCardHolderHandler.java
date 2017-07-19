package com.weststein.handler.card;

import com.weststein.controller.secured.model.CardHolderModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.UpdateCard;
import com.weststein.integration.response.AccountAPIv2UpdateCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateCardHolderHandler {

    @Autowired
    private PPFService<UpdateCard, AccountAPIv2UpdateCard> ppfService;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public AccountAPIv2UpdateCard handle(String cardHolderId, CardHolderModel updateCard) {
        UpdateCard updateCardObject = objectMapper.map(updateCard, UpdateCard.class);
        updateCardObject.setCardHolderId(cardHolderId);
        return ppfService.get(updateCardObject, AccountAPIv2UpdateCard.class);
    }
}
