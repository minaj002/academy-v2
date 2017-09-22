package com.weststein.controller.secured.model.business;

import com.weststein.controller.secured.model.RequestedUserModel;
import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.repository.UserToBusinessRoleRequest;
import com.weststein.repository.business.BusinessInformation;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class BusinessInformationToBusinessInformationModelMapper extends ObjectMapperConfiguration<BusinessInformation, BusinessInformationModel> {
    @Override
    public Class<BusinessInformation> getA() {
        return BusinessInformation.class;
    }

    @Override
    public Class<BusinessInformationModel> getB() {
        return BusinessInformationModel.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<BusinessInformation, BusinessInformationModel> builder) {

        builder
//                .field("cardholderIds{id}", "cardholderIds{}")
                .byDefault();

    }
}
