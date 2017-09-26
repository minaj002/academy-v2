package com.weststein.handler.administartion;

import com.weststein.controller.secured.business.model.business.AuthorizedUser;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GetUsersWithoutRolesHandler {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public List<AuthorizedUser> handle() {

        List<UserCredentials> userCredentials = userCredentialRepository.findUserCredentialsByRolesIsNullAndStatusNot(UserCredentials.Status.DELETED);
        List<AuthorizedUser> authorizedUsers = new ArrayList<>();
        userCredentials.forEach(user -> authorizedUsers.add(AuthorizedUser.builder()
                .userId(user.getId())
                .firstName(user.getUserProfile().getFirstName())
                .lastName(user.getUserProfile().getLastName())
                .build()));
        return authorizedUsers;
    }

}
