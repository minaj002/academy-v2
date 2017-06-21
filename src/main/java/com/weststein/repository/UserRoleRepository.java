package com.weststein.repository;

import com.weststein.security.model.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    UserRole findUserRoleByRole(String role);

}
