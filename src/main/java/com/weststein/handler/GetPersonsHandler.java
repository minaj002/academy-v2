package com.weststein.handler;

import com.weststein.integration.Person;
import com.weststein.integration.PersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonsHandler {

    @Autowired
    private PersonResource personResource;

    public List<Person> handle() {

        return personResource.getAll();

    }


}
