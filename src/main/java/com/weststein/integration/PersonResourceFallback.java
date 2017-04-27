package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResourceFallback implements PersonResource {


    @Override
    public List<SolarisPerson> getAll() {
        return null;
    }

    @Override
    public SolarisPerson getPerson(String personId) {
        return null;
    }

    @Override
    public SolarisPerson createPerson(SolarisPerson person) {
        return null;
    }

    @Override
    public SolarisPerson updatePerson(SolarisPerson person) {
        return null;
    }
}
