package com.weststein.handler.person;

import com.weststein.repository.Person;
import com.weststein.repository.PersonRepository;
import com.weststein.repository.Person_;
import com.weststein.repository.specifications.PersonSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

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
