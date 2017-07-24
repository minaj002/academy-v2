package com.weststein.handler.business;

import com.weststein.controller.secured.model.business.CompanyInformationModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.CompanyInformation;
import com.weststein.repository.CompanyInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateCompanyInfoHandler {

    @Autowired
    private CompanyInformationRepository companyInformationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(Long businessId, CompanyInformationModel companyInfoModel) {

        CompanyInformation companyInfo = objectMapper.map(companyInfoModel, CompanyInformation.class);
        companyInfo.setCreated(LocalDateTime.now());
        companyInfo.setBusinessId(businessId);
        companyInformationRepository.save(companyInfo);

    }

}
