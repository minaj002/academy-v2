package com.weststein.handler.application;

import com.weststein.controller.unsecured.model.ApplicationModel;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.*;
import com.weststein.security.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class ApplicationHandler {
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private PasswordEncoder encoder;

    public UserInformation handle(ApplicationModel applicationModel) {

        UserInformation application = objectMapper.map(applicationModel, UserInformation.class);
        application.setAgreeOn(LocalDateTime.now());
        application.setPhoneVerified(false);
        application = userInformationRepository.save(application);

        String verification = UUID.randomUUID().toString();

        UserCredentials credentials = createUserCredentials(applicationModel, application, verification);
        userCredentialRepository.save(credentials);

        emailSender.sendVerifyEmail(application.getEmail(), verification, application.getLanguage().name());
        return application;
    }

    private UserCredentials createUserCredentials(ApplicationModel applicationModel, UserInformation application, String verification) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.MEMBER);
        UserCredentials credentials = new UserCredentials();
        credentials.setEmail(application.getEmail());
        credentials.setPassword(encoder.encode(applicationModel.getPassword()));
        credentials.setVerified(Boolean.FALSE);
        credentials.setVerification(verification);
        credentials.setRoles(roles);
        return credentials;
    }
}
