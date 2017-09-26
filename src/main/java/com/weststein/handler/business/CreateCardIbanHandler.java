package com.weststein.handler.business;

import com.weststein.controller.secured.business.model.business.CardIbanModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.business.CardIban;
import com.weststein.repository.business.CardIbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateCardIbanHandler {

    @Autowired
    private CardIbanRepository cardIbanRepository;

    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(Long businessId, CardIbanModel cardIbanModel) {

        CardIban cardIban = objectMapper.map(cardIbanModel, CardIban.class);
        cardIban.setBusinessId(businessId);
        cardIban.setCreated(LocalDateTime.now());

        cardIbanRepository.save(cardIban);

    }

}
