package com.weststein.handler.person;

import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetPersonsHandler {

    @Autowired
    private PersonRepository personRepository;

    public Page<Person> handle(Pageable page) {
        //PageRequest pageRequest = new PageRequest(page, size);
        return personRepository.findAll(page);
    }

    public Page<Person> handle(String search, Pageable pageable) {
        return personRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search, search, pageable);
    }

}
