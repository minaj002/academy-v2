package com.weststein.handler.template;

import com.weststein.controller.secured.model.TransferTemplateModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.TransferTemplate;
import com.weststein.repository.TransferTemplateRepository;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class GetPaymentTemplateHandler {

    @Autowired
    private TransferTemplateRepository transferTemplateRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public TransferTemplateModel handle(Long templateId) {

        TransferTemplate transferTemplate = transferTemplateRepository.findOne(templateId);
        return objectMapper.map(transferTemplate, TransferTemplateModel.class);
    }
}
