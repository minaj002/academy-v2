package com.weststein.handler.application;

import com.weststein.infrastructure.exceptions.PasswordResetException;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ResetPasswordHandler {

    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void handle(String email, String password, String token) {

        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmailAndStatusNot(email, UserCredentials.Status.DELETED).get();
        if (credentials.getResetToken() == null) {
            throw new PasswordResetException("Password is already reset, please login using your email and password.");
        }
        if (token.equals(credentials.getResetToken())) {
            credentials.setResetToken(null);
            credentials.setPassword(encoder.encode(password));
            userCredentialRepository.save(credentials);
            return;
        }

        throw new PasswordResetException("Reset token is invalid, please follow link in your email.");
    }
}
