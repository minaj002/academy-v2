package com.weststein.handler.application;

import com.weststein.controller.secured.model.UserProfileModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.*;
import com.weststein.repository.business.BusinessInformationRepository;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import com.weststein.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RequestNewUserHandler {
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserToBusinessRoleRequestRepository userToBusinessRoleRequestRepository;

    public void handle(UserProfileModel userProfileModel) {
        emailValidator.validate(userProfileModel.getEmail());
        Long businessId = userService.getCurrentUserCredentials()
                .getRoles().stream()
                .filter(userRole -> Role.OWNER.equals(userRole.getRole()))
                .findFirst().get().getEntityId();
        UserCredentials credentials = createUserCredentials(userProfileModel, businessId);
        userCredentialRepository.save(credentials);

        UserToBusinessRoleRequest request = new UserToBusinessRoleRequest();
        request.setCreated(LocalDateTime.now());
        request.setStatus(UserToBusinessRoleRequest.Status.REQUESTED);
        request.setUser(credentials);
        request.setRequester(userService.getCurrentUserCredentials());
        request.setBusiness(businessInformationRepository.findOne(businessId));
        request.setRequestedRole(userProfileModel.getRole());
        userToBusinessRoleRequestRepository.save(request);

    }

    private UserCredentials createUserCredentials(UserProfileModel userProfileModel, Long businessId) {

        List<UserRole> roles = new ArrayList<>();
        UserRole role = UserRole.builder()
                .role(userProfileModel.getRole())
                .roleType(UserRole.RoleType.BUSINESS)
                .entityId(businessId).build();
        role = userRoleRepository.save(role);
        roles.add(role);
        UserProfile profile = objectMapper.map(userProfileModel, UserProfile.class);
        UserCredentials credentials = new UserCredentials();
        credentials.setUserProfile(profile);
        credentials.setEmail(userProfileModel.getEmail());
        credentials.setVerified(Boolean.FALSE);
        credentials.setRoles(roles);
        credentials.setStatus(UserCredentials.Status.REQUESTED);
        credentials.setLoginAttempt(0);
        return credentials;
    }
}
