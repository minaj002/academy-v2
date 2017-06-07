package com.weststein.handler.person;

import com.querydsl.core.types.Predicate;
import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetPersonsHandler {

    @Autowired
    private PersonRepository personRepository;

    public Page<Person> handle(Predicate personPredicate, Pageable page) {
        return personRepository.findAll(personPredicate, page);
    }

}
