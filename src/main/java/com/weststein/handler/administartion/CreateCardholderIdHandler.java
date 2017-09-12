package com.weststein.handler.administartion;

import com.weststein.controller.secured.model.CardholderIdModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateCardholderIdHandler {

    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public Long handle(CardholderIdModel cardholderIdModel) {

        CardholderId cardholderId = objectMapper.map(cardholderIdModel, CardholderId.class);
        cardholderId = cardholderIdRepository.save(cardholderId);
        return cardholderId.getId();
    }

}
