package com.weststein.integration;

import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.integration.request.CardIssue;
import com.weststein.repository.UserInformation;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationToCardIssueMapper extends ObjectMapperConfiguration<UserInformation, CardIssue> {
    @Override
    public Class<UserInformation> getA() {
        return UserInformation.class;
    }

    @Override
    public Class<CardIssue> getB() {
        return CardIssue.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<UserInformation, CardIssue> builder) {

        builder
                .field("dateOfBirth","dob")
                .field("address.country","countryCode")
                .field("address.postalCode","zipCode")
                .field("address.line1","address1")
                .field("address.line2","address2")
                .byDefault();

    }
}
