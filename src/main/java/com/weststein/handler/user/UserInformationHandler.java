package com.weststein.handler.user;

import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import com.weststein.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInformationHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInformationRepository userInformationRepository;

    public UserInformation handle() {
        return userInformationRepository.findByEmail(userService.getCurrentUser());
    }

}
