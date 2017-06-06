package com.weststein.handler.person;

import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import com.weststein.repository.specifications.PersonSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetPersonsHandler {

    @Autowired
    private PersonRepository personRepository;

    public Page<Person> handle(Pageable page) {

        return personRepository.findAll(page);
    }

    public Page<Person> handle(String search, Pageable pageable) {
        return personRepository.findAll(PersonSpecifications.findByFirstAndLastName(search), pageable);
    }

}
