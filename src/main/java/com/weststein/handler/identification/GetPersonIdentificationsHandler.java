package com.weststein.handler.identification;

import com.weststein.repository.Identification;
import com.weststein.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonIdentificationsHandler {

    @Autowired
    private IdentificationRepository identificationRepository;

    public List<Identification> handle(String personId) {
        return identificationRepository.findAllByPersonId(personId);
    }


}
