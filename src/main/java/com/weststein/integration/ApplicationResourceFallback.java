package com.weststein.integration;

import org.springframework.stereotype.Component;

@Component
public class ApplicationResourceFallback implements ApplicationResource {

    @Override
    public ApplicationRequest apply(ApplicationRequest application) {
        return application;
    }

}
