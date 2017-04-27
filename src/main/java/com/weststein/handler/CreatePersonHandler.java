package com.weststein.handler;

import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PersonResource;
import com.weststein.integration.SolarisPerson;
import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonHandler {

    @Autowired
    private PersonResource personResource;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public Person handle(Person person) {
        person.setDirty(true);
        person = personRepository.save(person);
        SolarisPerson solarisPerson = personResource.createPerson(objectMapper.map(person, SolarisPerson.class));
        if(solarisPerson.getSolarisId() != null) {
            person.setSolarisId(solarisPerson.getSolarisId());
            person.setDirty(false);
            personRepository.save(person);
        }
        return person;
    }
}
