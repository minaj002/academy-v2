package com.weststein.handler.user;

import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ChangePasswordHandler {
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void handle(String oldPassword, String newPassword) {

        UserCredentials credentials = userService.getCurrentUserCredentials();

        if (!passwordEncoder.matches(oldPassword, credentials.getPassword())) {
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().field("oldPassword").message("existing password does not match").build());
            throw new ValidationException(errors, "You must provide existing password to change it.");
        }
        credentials.setPassword(passwordEncoder.encode(newPassword));
        credentials.setStatus(UserCredentials.Status.ACTIVE);
        userCredentialRepository.save(credentials);

    }
}
