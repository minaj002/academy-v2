package com.weststein.handler.application;

import com.weststein.controller.unsecured.model.ApplicationModel;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.*;
import com.weststein.security.model.entity.Role;
import com.weststein.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private UserRoleRepository userRoleRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailValidator emailValidator;

    @Transactional
    public UserInformation handle(ApplicationModel applicationModel) {

        emailValidator.validate(applicationModel.getEmail());
        UserInformation application = objectMapper.map(applicationModel, UserInformation.class);
        application.setAgreeOn(LocalDateTime.now());
        application.setPhoneVerified(false);
        application = userInformationRepository.save(application);

        String verification = UUID.randomUUID().toString();

        UserCredentials credentials = createUserCredentials(applicationModel, application, verification);
        userCredentialRepository.save(credentials);

        emailSender.sendVerifyEmail(applicationModel.getEmail(), verification, applicationModel.getLanguage().name());
        return application;
    }

    private UserCredentials createUserCredentials(ApplicationModel applicationModel, UserInformation application, String verification) {
        List<UserRole> roles = new ArrayList<>();
        UserRole role = UserRole.builder().role(Role.PRIVATE).roleType(UserRole.RoleType.PRIVATE).entityId(application.getId()).build();
        role = userRoleRepository.save(role);
        roles.add(role);
        UserProfile profile = objectMapper.map(applicationModel, UserProfile.class);
        UserCredentials credentials = new UserCredentials();
        credentials.setUserProfile(profile);
        credentials.setEmail(applicationModel.getEmail());
        credentials.setPassword(encoder.encode(applicationModel.getPassword()));
        credentials.setVerified(Boolean.FALSE);
        credentials.setVerification(verification);
        credentials.setRoles(roles);
        credentials.setStatus(UserCredentials.Status.ACTIVE);
        return credentials;
    }
}
