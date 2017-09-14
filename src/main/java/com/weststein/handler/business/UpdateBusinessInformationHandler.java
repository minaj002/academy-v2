package com.weststein.handler.business;

import com.weststein.controller.secured.model.business.BusinessInformationModel;
import com.weststein.infrastructure.EntityUpdater;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessInformationHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private EntityUpdater<BusinessInformation> entityUpdater;

    public BusinessInformationModel handle(Long businessId, BusinessInformationModel businessInformationModel) {
        businessInformationModel.setId(businessId);
        BusinessInformation businessInfo = objectMapper.map(businessInformationModel, BusinessInformation.class);

        BusinessInformation existing = businessInformationRepository.findOne(businessId);

        entityUpdater.apply(businessInfo, existing);
        businessInformationRepository.save(existing);

        return objectMapper.map(existing, BusinessInformationModel.class);
    }
}
