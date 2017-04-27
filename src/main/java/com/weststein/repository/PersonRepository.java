package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findBySolarisId(String solarisId);

}
