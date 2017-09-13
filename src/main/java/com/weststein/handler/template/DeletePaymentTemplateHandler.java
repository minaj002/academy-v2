package com.weststein.handler.template;

import com.weststein.repository.TransferTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeletePaymentTemplateHandler {

    @Autowired
    private TransferTemplateRepository transferTemplateRepository;

    public void handle(Long templateId) {
        transferTemplateRepository.delete(templateId);
    }
}
