package com.weststein.handler;

import com.weststein.controller.secured.model.ApplicationForm;
import com.weststein.controller.secured.model.ApplicationResponse;
import com.weststein.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
