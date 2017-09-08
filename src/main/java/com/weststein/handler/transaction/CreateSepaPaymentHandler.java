package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.SepaPayment;
import com.weststein.integration.response.AccountAPIv2SepaPayment;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Component
public class CreateSepaPaymentHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private SepaTransferRepository sepaTransferRepository;

    public Long handle(Long id, SepaTransferModel sepa) {

        CardholderId cardholderId = cardholderIdRepository.findOne(id);

        SepaTransfer sepaTransferEntity = objectMapper.map(sepa, SepaTransfer.class);
        sepaTransferEntity.setFrom(cardholderId);
        sepaTransferEntity.setStatus(SepaTransfer.Status.DRAFT);
        sepaTransferEntity.setCreated(LocalDateTime.now());
        sepaTransferEntity.setPredictedFee(BigDecimal.valueOf(0.55));

        sepaTransferEntity = sepaTransferRepository.save(sepaTransferEntity);
        return sepaTransferEntity.getId();
    }
}
