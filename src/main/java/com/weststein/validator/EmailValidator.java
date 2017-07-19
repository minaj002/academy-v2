package com.weststein.validator;

import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmailValidator {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public void validate(String email) {

        Optional<UserCredentials> userCredential = userCredentialRepository.findUserCredentialsByEmail(email);
        userCredential.ifPresent(userCredentials -> {
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().field("email").message("this email is already registered").build());
            throw new ValidationException(errors, "this email is already registered");
        });
    }
}
