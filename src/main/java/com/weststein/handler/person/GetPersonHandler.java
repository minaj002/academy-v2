package com.weststein.handler.person;

import com.weststein.infrastructure.exceptions.ResourceNotFoundException;
import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonHandler {

    @Autowired
    private PersonRepository personRepository;

    public Person handle(String solarisId) {
        return personRepository.findBySolarisId(solarisId).orElseThrow(() -> new ResourceNotFoundException(String.format("Person with id: %s does not exist", solarisId)));
    }

}
