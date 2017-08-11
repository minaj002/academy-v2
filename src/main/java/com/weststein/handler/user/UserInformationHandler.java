package com.weststein.handler.user;

import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import com.weststein.repository.UserRole;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserInformationHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInformationRepository userInformationRepository;

    public UserInformation handle() {
        UserCredentials credentials = userService.getCurrentUserCredentials();
        UserRole privateRole = credentials.getRoles().stream().filter(userRole -> Role.PRIVATE.equals(userRole.getRole())).findFirst().get();
        return userInformationRepository.findOne(privateRole.getEntityId());
    }

}
