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
public class GetPaymentTemplatesHandler {

    @Autowired
    private TransferTemplateRepository transferTemplateRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Transactional
    public List<TransferTemplateModel> handle(Long businessId, int size, int page) {

        BusinessInformation businessInformation = businessInformationRepository.findOne(businessId);
        Page<TransferTemplate> transferTemplates = transferTemplateRepository.findAllByBusinessOrderByName(businessInformation, new PageRequest(page - 1, size));
        return objectMapper.map(transferTemplates.getContent(), TransferTemplateModel.class);
    }
}
