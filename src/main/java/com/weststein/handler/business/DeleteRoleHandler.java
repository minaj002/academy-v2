package com.weststein.handler.business;

import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserRole;
import com.weststein.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteRoleHandler {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public void handle(Long businessId, Long roleId) {

        UserRole role = userRoleRepository.findOne(roleId);
        if(!businessId.equals(role.getEntityId())){
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().field("roleId").message("role does not belong o this business").build());
            throw new ValidationException(errors, "User is not authorized to remove this role");
        }
        UserCredentials authorizedUser = userCredentialRepository.findUserCredentialsByRolesContainsAndStatusNot(role, UserCredentials.Status.DELETED).get();
        authorizedUser.getRoles().remove(role);
        userCredentialRepository.save(authorizedUser);
        userRoleRepository.delete(role);

    }

}
