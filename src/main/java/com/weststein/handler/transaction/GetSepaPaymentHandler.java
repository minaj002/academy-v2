package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetSepaPaymentHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private SepaTransferRepository sepaTransferRepository;

    public SepaTransferModel handle(Long id) {
        SepaTransfer sepaTransferEntity = sepaTransferRepository.findOne(id);
        return objectMapper.map(sepaTransferEntity, SepaTransferModel.class);
    }
}
