package com.weststein.handler.application;

import com.weststein.controller.unsecured.model.ApplicationModel;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardIssue;
import com.weststein.integration.response.AccountAPIv2CardIssue;
import com.weststein.repository.*;
import com.weststein.security.model.entity.Role;
import com.weststein.security.model.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class ApplicationHandler {
    private static final String cardStyle = "01";
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private PPFService<CardIssue, AccountAPIv2CardIssue> ppfService1;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private PasswordEncoder encoder;

    public Application handle(ApplicationModel applicationModel) {

        Application application = objectMapper.map(applicationModel, Application.class);
        application.setAgreeOn(LocalDateTime.now());

        application = applicationRepository.save(application);

        CardIssue cardRequest = createCardRequest(application);

        AccountAPIv2CardIssue res2 = ppfService1.get(cardRequest, AccountAPIv2CardIssue.class);

        String verification = UUID.randomUUID().toString();

        UserCredentials credentials = createUserCredentials(applicationModel, application, res2, verification);
        userCredentialRepository.save(credentials);

        emailSender.sendWelcomeEmail(application.getEmail(), verification);
        return application;
    }

    private CardIssue createCardRequest(Application application) {
        CardIssue cardRequest = objectMapper.map(application, CardIssue.class);
        cardRequest.setBin("59991198");
        cardRequest.setVerifyDOBOverride("Y");
        cardRequest.setVerifySSNOverride("Y");
        cardRequest.setGeoIPCheckOverride("Y");
        cardRequest.setUserDefinedField3("SOLO");
        cardRequest.setUserDefinedField2("1");
        cardRequest.setCardStyle(cardStyle);
        cardRequest.setDistributorCode("723");
        return cardRequest;
    }

    private UserCredentials createUserCredentials(ApplicationModel applicationModel, Application application, AccountAPIv2CardIssue res2, String verification) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.MEMBER);
        Set<String> cardHolderIds = new HashSet<>();
        cardHolderIds.add(res2.getCardIssue().getCardHolderId());
        UserCredentials credentials = new UserCredentials();
        credentials.setEmail(application.getEmail());
        credentials.setPassword(encoder.encode(applicationModel.getPassword()));
        credentials.setCardHolderIds(cardHolderIds);
        credentials.setVerified(Boolean.FALSE);
        credentials.setVerification(verification);
        credentials.setRoles(roles);
        return credentials;
    }
}
