package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserInformationRepository extends CrudRepository<UserInformation, Long> {

    UserInformation findByEmail(String email);

}
