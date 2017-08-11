package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserToBusinessRoleRequestRepository extends CrudRepository<UserToBusinessRoleRequest, Long> {

    List<UserToBusinessRoleRequest> findByStatus(UserToBusinessRoleRequest.Status status);
}
