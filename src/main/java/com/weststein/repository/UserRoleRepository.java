package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    List<UserRole> findByEntityIdAndRoleType(Long entityId, UserRole.RoleType roleType);

}
