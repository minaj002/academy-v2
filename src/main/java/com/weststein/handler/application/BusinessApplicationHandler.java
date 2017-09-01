package com.weststein.handler.application;

import com.weststein.controller.unsecured.model.BusinessApplicationModel;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.*;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import com.weststein.security.model.entity.Role;
import com.weststein.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        userInformationRepository.save(application);

        BusinessInformation businessInformation = objectMapper.map(applicationModel, BusinessInformation.class);
        businessInformation = businessInformationRepository.save(businessInformation);

        String verification = UUID.randomUUID().toString();

        UserCredentials credentials = createUserCredentials(applicationModel, businessInformation, verification);
        userCredentialRepository.save(credentials);

        emailSender.sendVerifyEmail(applicationModel.getEmail(), verification, applicationModel.getLanguage().name());
    }

    private UserCredentials createUserCredentials(BusinessApplicationModel applicationModel, BusinessInformation businessInformation, String verification) {
        List<UserRole> roles = new ArrayList<>();
        UserRole role = UserRole.builder().role(Role.OWNER).entityId(businessInformation.getId()).roleType(UserRole.RoleType.BUSINESS).build();
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
        credentials.setLoginAttempt(0);
        return credentials;
    }
}
