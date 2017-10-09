package com.academy.repository;

import com.academy.core.domain.Academy;
import org.springframework.data.repository.CrudRepository;

public interface AcademyRepository extends CrudRepository<Academy, Long> {
//public interface AcademyRepository extends MongoRepository<Academy, String>{

}
