package com.weststein.handler.business;

import com.weststein.controller.secured.model.business.BusinessInformationModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.business.BusinessInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetBusinessInformationHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;

    public BusinessInformationModel handle(Long businessId) {
        return objectMapper.map(businessInformationRepository.findOne(businessId), BusinessInformationModel.class);
    }

}
