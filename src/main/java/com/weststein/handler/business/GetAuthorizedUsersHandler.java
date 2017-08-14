package com.weststein.handler.business;

import com.weststein.controller.secured.model.business.AuthorizedUser;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserRole;
import com.weststein.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAuthorizedUsersHandler {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public List<AuthorizedUser> handle(Long businessId) {
        List<UserRole> roles = userRoleRepository.findByEntityIdAndRoleType(businessId, UserRole.RoleType.BUSINESS);
        List<AuthorizedUser> authorizedUsers = new ArrayList<>();
        roles.forEach(role -> {
            UserCredentials authorizedUser = userCredentialRepository.findUserCredentialsByRolesContains(role);
            authorizedUsers.add(AuthorizedUser.builder()
                    .firstName(authorizedUser.getUserProfile().getFirstName())
                    .lastName(authorizedUser.getUserProfile().getLastName())
                    .role(role.getRole())
                    .roleId(role.getId())
                    .build());

        });
        return authorizedUsers;
    }

}
