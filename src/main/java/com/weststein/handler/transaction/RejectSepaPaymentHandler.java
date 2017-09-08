package com.weststein.handler.transaction;

import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RejectSepaPaymentHandler {

    @Autowired
    private SepaTransferRepository sepaTransferRepository;

    public void handle(Long id) {
        SepaTransfer sepaTransferEntity = sepaTransferRepository.findOne(id);
        sepaTransferEntity.setStatus(SepaTransfer.Status.REJECTED);
        sepaTransferRepository.save(sepaTransferEntity);
    }
}
