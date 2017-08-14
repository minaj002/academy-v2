package com.weststein.handler.application;

import com.weststein.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DeleteUserHandler {

    @Autowired
    private UserToBusinessRoleRequestRepository userToBusinessRoleRequestRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    public void handle(Long id) {

        UserCredentials credentialsToDelete = userCredentialRepository.findOne(id);
        List<UserToBusinessRoleRequest> userToBusinessRoleRequest = userToBusinessRoleRequestRepository.findUserToBusinessRoleRequestsByUser(credentialsToDelete);
        userToBusinessRoleRequestRepository.delete(userToBusinessRoleRequest);
        List<UserRole> roles = new ArrayList<>(credentialsToDelete.getRoles());
        credentialsToDelete.getRoles().clear();
        credentialsToDelete.setStatus(UserCredentials.Status.DELETED);
        userCredentialRepository.save(credentialsToDelete);
        userRoleRepository.delete(roles);
    }

}
