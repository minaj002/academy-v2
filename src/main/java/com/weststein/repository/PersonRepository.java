package com.weststein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Optional<Person> findBySolarisId(String solarisId);
    List<Person> findAllBySolarisIdIn(List<String> ids);
    Page<Person> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);

}
