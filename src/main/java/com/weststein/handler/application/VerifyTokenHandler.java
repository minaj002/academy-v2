package com.weststein.handler.application;

import com.weststein.infrastructure.MessageBean;
import com.weststein.infrastructure.exceptions.AccountVerificationException;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerifyTokenHandler {

    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private MessageBean messageBean;

    public void handle(String email, String token) {

        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmail(email).get();
        if(credentials.getVerified()) {
            throw new AccountVerificationException("Account is already verified, please login using your email and password.");
        }
        if(token.equals(credentials.getVerification())) {
            credentials.setVerified(true);
            credentials.setVerification(null);
            userCredentialRepository.save(credentials);
            if(UserCredentials.Status.REQUESTED.equals(credentials.getStatus())) {
                messageBean.add("Password reset required");
            }
            return;
        }

        throw new AccountVerificationException("Account is not verified, please follow link in your email.");
    }
}
