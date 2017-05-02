package com.weststein.handler.person;

import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetPersonsHandler {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> handle() {
       return StreamUtils.createStreamFromIterator(personRepository.findAll().iterator()).collect(Collectors.toList());
    }

}
