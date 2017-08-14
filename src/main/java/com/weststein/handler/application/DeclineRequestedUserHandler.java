package com.weststein.handler.application;

import com.weststein.email.EmailSender;
import com.weststein.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class DeclineRequestedUserHandler {

    @Autowired
    private UserToBusinessRoleRequestRepository userToBusinessRoleRequestRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private EmailSender emailSender;

    @Transactional
    public void handle(Long id) {
        UserToBusinessRoleRequest requestToDecline = userToBusinessRoleRequestRepository.findOne(id);
        requestToDecline.setStatus(UserToBusinessRoleRequest.Status.DECLINED);
        userToBusinessRoleRequestRepository.save(requestToDecline);

        Long businessId = requestToDecline.getBusiness().getId();
        UserCredentials requestedUser = requestToDecline.getUser();
        List<UserRole> roles = requestedUser.getRoles();
        UserRole roleToBeRemoved = roles.stream().filter(role -> UserRole.RoleType.BUSINESS.equals(role.getRoleType()) && businessId.equals(role.getEntityId())).findFirst().get();
        requestedUser.getRoles().remove(roleToBeRemoved);
        userRoleRepository.delete(roleToBeRemoved);
        if(UserCredentials.Status.REQUESTED.equals(requestedUser.getStatus())&& requestedUser.getRoles().isEmpty()) {
            requestedUser.setStatus(UserCredentials.Status.DELETED);
        }
        userCredentialRepository.save(requestedUser);

        emailSender.sendDeclinedEmail(requestedUser.getEmail(), requestToDecline.getRequester().getUserProfile().getFirstName() + " " + requestToDecline.getRequester().getUserProfile().getLastName(), requestToDecline.getRequestedRole().name(), requestToDecline.getBusiness().getEnterpriseName() ,requestedUser.getUserProfile().getLanguage().name());
        emailSender.sendDeclinedOwnerEmail(requestToDecline.getRequester().getEmail(), requestToDecline.getUser().getUserProfile().getFirstName() + " " + requestToDecline.getUser().getUserProfile().getLastName(), requestToDecline.getRequestedRole().name(), requestToDecline.getBusiness().getEnterpriseName(),requestToDecline.getRequester().getUserProfile().getLanguage().name());

    }

}
