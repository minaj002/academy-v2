package com.weststein.handler.application;

import com.weststein.email.EmailSender;
import com.weststein.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ConfirmRequestedUserHandler {

    @Autowired
    private UserToBusinessRoleRequestRepository userToBusinessRoleRequestRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void handle(Long id) {
        UserToBusinessRoleRequest requestToAccept = userToBusinessRoleRequestRepository.findOne(id);
        requestToAccept.setStatus(UserToBusinessRoleRequest.Status.GRANTED);
        userToBusinessRoleRequestRepository.save(requestToAccept);

        UserCredentials requestedUser = requestToAccept.getUser();
        if (UserCredentials.Status.REQUESTED.equals(requestedUser.getStatus())) {
            requestedUser.setStatus(UserCredentials.Status.ACTIVE);
            String tempPassword = new BigInteger(130, new SecureRandom()).toString(32);
            requestedUser.setPassword(encoder.encode(tempPassword));
            String verification = UUID.randomUUID().toString();
            requestedUser.setVerification(verification);
            requestedUser.setVerified(false);
            emailSender.sendAcceptedNewUserEmail(requestedUser.getEmail(), requestToAccept.getRequester().getUserProfile().getFirstName() + " " + requestToAccept.getRequester().getUserProfile().getLastName(), requestToAccept.getRequestedRole().name(), requestToAccept.getBusiness().getEnterpriseName(), verification, tempPassword, requestedUser.getUserProfile().getLanguage().name());
            emailSender.sendAcceptedNewUserOwnerEmail(requestToAccept.getRequester().getEmail(), requestToAccept.getUser().getUserProfile().getFirstName() + " " + requestToAccept.getUser().getUserProfile().getLastName(), requestToAccept.getRequestedRole().name(), requestToAccept.getBusiness().getEnterpriseName(), requestedUser.getUserProfile().getLanguage().name());
        } else {
            emailSender.sendAcceptedExistingUserEmail(requestedUser.getEmail(), requestToAccept.getUser().getUserProfile().getFirstName() + " " + requestToAccept.getUser().getUserProfile().getLastName(), requestToAccept.getRequestedRole().name(), requestToAccept.getBusiness().getEnterpriseName(), requestedUser.getUserProfile().getLanguage().name());
            emailSender.sendAcceptedExistingUserOwnerEmail(requestToAccept.getRequester().getEmail(), requestToAccept.getUser().getUserProfile().getFirstName() + " " + requestToAccept.getUser().getUserProfile().getLastName(), requestToAccept.getRequestedRole().name(), requestToAccept.getBusiness().getEnterpriseName(), requestedUser.getUserProfile().getLanguage().name());

        }
        userCredentialRepository.save(requestedUser);
    }

}
