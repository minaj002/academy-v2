package com.academy.repository;

import com.academy.core.domain.AcademyUser;
import org.springframework.data.repository.CrudRepository;

public interface AcademyUserRepository extends CrudRepository<AcademyUser, Long> {

    AcademyUser findByName(String academyUsername);

}
