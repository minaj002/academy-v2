package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findBySolarisId(String solarisId);
    List<Person> findAllBySolarisIdIn(List<String> ids);

}
