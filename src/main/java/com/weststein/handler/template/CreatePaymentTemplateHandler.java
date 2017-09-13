package com.weststein.handler.template;

import com.weststein.controller.secured.model.TransferTemplateModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.TransferTemplate;
import com.weststein.repository.TransferTemplateRepository;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
public class CreatePaymentTemplateHandler {

    @Autowired
    private TransferTemplateRepository transferTemplateRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Transactional
    public Long handle(Long businessId, TransferTemplateModel transferTemplateModel) {

        BusinessInformation business = businessInformationRepository.findOne(businessId);
        TransferTemplate transferTemplate = objectMapper.map(transferTemplateModel, TransferTemplate.class);
        transferTemplate.setBusiness(business);
        transferTemplate.setCreated(LocalDateTime.now());
        transferTemplate = transferTemplateRepository.save(transferTemplate);
        return transferTemplate.getId();
    }
}
