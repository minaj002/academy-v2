package com.weststein.handler.identification;

import com.weststein.repository.Identification;
import com.weststein.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonIdentificationHandler {

    @Autowired
    private IdentificationRepository identificationRepository;

    public Identification handle(String id) {
        return identificationRepository.findBySolarisId(id);
    }

}
