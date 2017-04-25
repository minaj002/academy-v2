package com.weststein.handler;

import com.weststein.controller.secured.model.ApplicationForm;
import com.weststein.controller.secured.model.ApplicationResponse;
import com.weststein.integration.CardIssue;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.ApplicationRequest;
import com.weststein.integration.ApplicationResource;
import com.weststein.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHandler {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationResource applicationResource;
    @Autowired
    private OrikoObjectMapper mapper;

    public ApplicationResponse handle(ApplicationForm application) {
        application = applicationRepository.save(application);

        CardIssue cardIssue = mapper.map(application, CardIssue.class);
        ApplicationRequest applicationRequest = ApplicationRequest.builder().data(cardIssue).userName("test").password("Password").build();

       // ApplicationRequest result = applicationResource.apply(applicationRequest);
        ApplicationRequest result = ApplicationRequest.builder().build();
        ApplicationResponse applicationResponse = ApplicationResponse.builder().data(result.getData()).build();
        return applicationResponse;
    }

}
