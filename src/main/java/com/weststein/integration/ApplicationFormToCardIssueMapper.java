package com.weststein.integration;

import com.weststein.controller.model.ApplicationForm;
import com.weststein.infrastructure.ObjectMapperConfiguration;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFormToCardIssueMapper extends ObjectMapperConfiguration <ApplicationForm, CardIssue>{
    @Override
    public Class<ApplicationForm> getA() {
        return ApplicationForm.class;
    }

    @Override
    public Class<CardIssue> getB() {
        return CardIssue.class;
    }


    @Override
    protected void fieldMapping(ClassMapBuilder<ApplicationForm, CardIssue> builder) {

        builder
                .field("secondName", "lastName")
                .field("dateOfBirth","dob")
                .field("mobilePhone","phone")
                .field("country","countryCode")
                .field("postalCode","zipCode")
                .field("securityQuestion1","securityField1")
                .field("answer1","securityField2")

                .byDefault();

    }
}
