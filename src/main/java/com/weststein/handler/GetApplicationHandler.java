package com.weststein.handler;

import com.weststein.controller.model.ApplicationForm;
import com.weststein.controller.model.ApplicationResponse;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.ApplicationRequest;
import com.weststein.integration.ApplicationResource;
import com.weststein.integration.CardIssue;
import com.weststein.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GetApplicationHandler {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<ApplicationForm> handle() {
        Iterable<ApplicationForm> applications = applicationRepository.findAll();
        return StreamSupport.stream(applications.spliterator(), false).collect(Collectors.<ApplicationForm>toList());
    }

}
