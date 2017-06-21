package com.weststein.integration;

import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.integration.request.CardIssue;
import com.weststein.repository.Application;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationToCardIssueMapper extends ObjectMapperConfiguration<Application, CardIssue> {
    @Override
    public Class<Application> getA() {
        return Application.class;
    }

    @Override
    public Class<CardIssue> getB() {
        return CardIssue.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<Application, CardIssue> builder) {

        builder
                .field("dateOfBirth","dob")
                .field("address.country","countryCode")
                .field("address.postalCode","zipCode")
                .field("address.line1","address1")
                .field("address.line2","address2")
                .byDefault();

    }
}
