package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findBySolarisId(String solarisId);
    List<Person> findAllBySolarisIdIn(List<String> ids);

}
