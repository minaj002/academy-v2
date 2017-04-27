package com.weststein.handler;

import com.weststein.infrastructure.EntityUpdater;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PersonResource;
import com.weststein.integration.SolarisPerson;
import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonHandler {

    @Autowired
    private PersonResource personResource;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private EntityUpdater entityUpdater;

    public Person handle(Person person) {
        Person existingPerson = personRepository.findBySolarisId(person.getSolarisId());
        entityUpdater.apply(person, existingPerson);
        existingPerson.setDirty(true);

        existingPerson = personRepository.save(existingPerson);
        SolarisPerson solarisPerson = personResource.updatePerson(objectMapper.map(person, SolarisPerson.class));
        if(solarisPerson.getSolarisId() != null){
            existingPerson.setDirty(false);
            personRepository.save(existingPerson);
        }
        return existingPerson;
    }


}
