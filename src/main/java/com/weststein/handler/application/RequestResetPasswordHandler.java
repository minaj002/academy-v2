package com.weststein.handler.application;

import com.weststein.email.EmailSender;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class RequestResetPasswordHandler {

    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private EmailSender emailSender;

    public void handle(String email) {

        Optional<UserCredentials> credentialsOptional = userCredentialRepository.findUserCredentialsByEmail(email);
        if (credentialsOptional.isPresent()) {
            String resetToken = UUID.randomUUID().toString();
            UserCredentials credentials = credentialsOptional.get();
            credentials.setResetToken(resetToken);
            userCredentialRepository.save(credentials);
            emailSender.sendResetPasswordEmail(email, resetToken, credentials.getUserProfile().getLanguage().name());
        } else {
            // no message if email is incorrect
            return;
        }

    }
}
