package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonResourceFallback implements PersonResource {


    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public FullPerson getPerson(String personId) {
        return null;
    }
}
