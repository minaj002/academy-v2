package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ConfirmSepaPaymentHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private SepaTransferRepository sepaTransferRepository;

    public SepaTransferModel handle(Long id, BigDecimal amount) {
        SepaTransfer sepaTransferEntity = sepaTransferRepository.findOne(id);

        if(amount.compareTo(sepaTransferEntity.getAmount())!=0) {
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().field("amount").message("Amount must match").build());
            throw new ValidationException(errors, "Amount must match");
        }
        sepaTransferEntity.setStatus(SepaTransfer.Status.SIGNING);
        sepaTransferEntity = sepaTransferRepository.save(sepaTransferEntity);
        return objectMapper.map(sepaTransferEntity, SepaTransferModel.class);
    }
}
