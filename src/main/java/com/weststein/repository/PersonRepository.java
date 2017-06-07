package com.weststein.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, QueryDslPredicateExecutor<Person> {

    Optional<Person> findBySolarisId(String solarisId);
    List<Person> findAllBySolarisIdIn(List<String> ids);

}
