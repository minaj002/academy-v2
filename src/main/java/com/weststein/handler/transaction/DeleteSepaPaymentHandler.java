package com.weststein.handler.transaction;

import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DeleteSepaPaymentHandler {

    @Autowired
    private SepaTransferRepository sepaTransferRepository;

    @Transactional
    public void handle(Long id) {
        SepaTransfer sepaTransferEntity = sepaTransferRepository.findOne(id);
        sepaTransferEntity.setStatus(SepaTransfer.Status.DELETED);
        sepaTransferRepository.save(sepaTransferEntity);
    }
}
