package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.SepaPayment;
import com.weststein.integration.response.AccountAPIv2SepaPayment;
import com.weststein.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SepaPaymentHandler {

    @Autowired
    private PPFService<SepaPayment, AccountAPIv2SepaPayment> ppfService;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private SepaTransferRepository sepaTransferRepository;

    public SepaTransferModel handle(Long id) {

        SepaTransfer sepaTransfer = sepaTransferRepository.findOne(id);
        sepaTransfer.setPaymentDate(LocalDateTime.now());
        SepaPayment sepaPayment = objectMapper.map(sepaTransfer, SepaPayment.class);

        try {
            AccountAPIv2SepaPayment response = ppfService.get(sepaPayment,
                    AccountAPIv2SepaPayment.class);
            sepaTransfer.setReferenceId(response.getReferenceID());
            sepaTransfer.setStatus(SepaTransfer.Status.COMPLETED);
            sepaTransferRepository.save(sepaTransfer);
        } catch (ValidationException e) {
            sepaTransfer.setStatus(SepaTransfer.Status.DECLINED);
            sepaTransferRepository.save(sepaTransfer);
        }

        return objectMapper.map(sepaTransfer, SepaTransferModel.class);
    }
}
