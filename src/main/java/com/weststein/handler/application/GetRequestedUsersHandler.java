package com.weststein.handler.application;

import com.weststein.controller.secured.model.RequestedUserModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserToBusinessRoleRequest;
import com.weststein.repository.UserToBusinessRoleRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetRequestedUsersHandler {

    @Autowired
    private UserToBusinessRoleRequestRepository userToBusinessRoleRequestRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public List<RequestedUserModel> handle() {
        return objectMapper.map(userToBusinessRoleRequestRepository.findByStatus(UserToBusinessRoleRequest.Status.REQUESTED), RequestedUserModel.class);
    }

}
