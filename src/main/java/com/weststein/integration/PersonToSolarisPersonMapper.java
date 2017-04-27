package com.weststein.integration;

import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.repository.Person;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class PersonToSolarisPersonMapper extends ObjectMapperConfiguration <Person, SolarisPerson>{
    @Override
    public Class<Person> getA() {
        return Person.class;
    }

    @Override
    public Class<SolarisPerson> getB() {
        return SolarisPerson.class;
    }


    @Override
    protected void fieldMapping(ClassMapBuilder<Person, SolarisPerson> builder) {

        builder
                .byDefault();

    }
}
