package com.weststein.handler.application;

import com.weststein.controller.unsecured.model.ApplicationModel;
import com.weststein.controller.unsecured.model.BusinessApplicationModel;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.*;
import com.weststein.security.model.entity.Role;
import com.weststein.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class BusinessApplicationHandler {
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
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

    public void handle(BusinessApplicationModel applicationModel) {

        emailValidator.validate(applicationModel.getEmail());

        UserInformation application = objectMapper.map(applicationModel, UserInformation.class);
        application.setAgreeOn(LocalDateTime.now());
        application.setPhoneVerified(false);
        application = userInformationRepository.save(application);

        BusinessInformation businessInformation = objectMapper.map(applicationModel, BusinessInformation.class);

        businessInformation = businessInformationRepository.save(businessInformation);

        String verification = UUID.randomUUID().toString();

        UserCredentials credentials = createUserCredentials(applicationModel, businessInformation, verification);
        userCredentialRepository.save(credentials);

        emailSender.sendVerifyEmail(application.getEmail(), verification, application.getLanguage().name());
    }

    private UserCredentials createUserCredentials(BusinessApplicationModel applicationModel, BusinessInformation businessInformation, String verification) {
        List<UserRole> roles = new ArrayList<>();
        UserRole role = UserRole.builder().role(Role.OWNER).entityId(businessInformation.getId()).roleType(UserRole.RoleType.BUSINESS).build();
        role = userRoleRepository.save(role);
        roles.add(role);
        UserCredentials credentials = new UserCredentials();
        credentials.setEmail(applicationModel.getEmail());
        credentials.setPassword(encoder.encode(applicationModel.getPassword()));
        credentials.setVerified(Boolean.FALSE);
        credentials.setVerification(verification);
        credentials.setRoles(roles);
        return credentials;
    }
}
