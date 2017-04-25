package com.weststein.handler;

import com.weststein.integration.FullPerson;
import com.weststein.integration.Person;
import com.weststein.integration.PersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonHandler {

    @Autowired
    private PersonResource personResource;

    public FullPerson handle(String personId) {
        return personResource.getPerson(personId);
    }


}
