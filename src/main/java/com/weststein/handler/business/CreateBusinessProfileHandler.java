package com.weststein.handler.business;

import com.weststein.controller.secured.business.model.business.BusinessProfileModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.business.BusinessProfile;
import com.weststein.repository.business.BusinessProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateBusinessProfileHandler {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(Long businessId, BusinessProfileModel businessProfileModel) {

        BusinessProfile businessProfile = objectMapper.map(businessProfileModel, BusinessProfile.class);
        businessProfile.setBusinessId(businessId);
        businessProfile.setCreated(LocalDateTime.now());

        businessProfileRepository.save(businessProfile);

    }

}
