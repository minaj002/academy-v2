package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdentificationResourceFallback implements IdentificationResource {


    @Override
    public List<SolarisIdentification> getAll(String personId) {
        return null;
    }

    @Override
    public SolarisIdentification getIdentification(String personId, String id) {
        return null;
    }

}
