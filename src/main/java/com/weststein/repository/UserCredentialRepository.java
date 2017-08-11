package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCredentialRepository extends CrudRepository<UserCredentials, Long> {

    Optional<UserCredentials> findUserCredentialsByEmail(String email);
    Optional<UserCredentials> findUserCredentialsByEmailAndStatusNot(String email, UserCredentials.Status status);
    List<UserCredentials> findAllByStatus(UserCredentials.Status status);

}
