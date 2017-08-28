package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCredentialRepository extends CrudRepository<UserCredentials, Long> {

    Optional<UserCredentials> findUserCredentialsByEmailAndStatusNot(String email, UserCredentials.Status status);
    Optional<UserCredentials> findUserCredentialsByRolesContainsAndStatusNot(UserRole role, UserCredentials.Status status);
    List<UserCredentials> findUserCredentialsByRolesIsNullAndStatusNot(UserCredentials.Status status);

}
