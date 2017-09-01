package com.weststein.security;


import com.weststein.infrastructure.MessageBean;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.*;
import com.weststein.security.model.UserContext;
import com.weststein.security.model.entity.Role;
import com.weststein.security.model.token.JwtToken;
import com.weststein.security.model.token.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    public static final String RESET_YOUR_PASSWORD = "Please reset your password";
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private MessageBean messageBean;

    public void update(UserCredentials userCredentials) {
        userCredentialRepository.save(userCredentials);
    }

    public String getCurrentUser() {
        return ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Optional<UserCredentials> getByUsername(String username) {
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmailAndStatusNot(username, UserCredentials.Status.DELETED).get();
        return Optional.of(credentials);
    }

    public UserCredentials getCurrentUserCredentials() {
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmailAndStatusNot(getCurrentUser(), UserCredentials.Status.DELETED).get();
        return credentials;
    }

    public String startSession() {
        UserContext context = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtToken accessToken = tokenFactory.createAccessJwtToken(context);
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmailAndStatusNot(context.getUsername(), UserCredentials.Status.DELETED).get();
        credentials.setToken(accessToken.getToken());
        userCredentialRepository.save(credentials);
        if(credentials.getStatus().equals(UserCredentials.Status.REQUESTED) && credentials.getVerified()) {
            messageBean.add(RESET_YOUR_PASSWORD);
        }
        return accessToken.getToken();
    }

    public void isAuthorizedForCardHolder(Long cardholderId) {
        List<UserRole> usersCardholdersIds = ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserRoles();

        if (usersCardholdersIds.stream().noneMatch(userRole -> {
            if (Role.PRIVATE.equals(userRole.getRole())) {
                UserInformation userInformation = userInformationRepository.findOne(userRole.getEntityId());
                return userInformation.getCardholderIds().stream().anyMatch(cardHolder -> cardHolder.getId().equals(cardholderId));

            }
            return true;
            // TODO: JM verify id for business accounts
        })) {

            List errors = new ArrayList();
            errors.add(ValidationError.builder().field("cardholderId").message("Not Authorized").build());
            throw new ValidationException(errors, "User is not authorized to see information for cardholder " + cardholderId);

        }
    }

    public void isAuthorizedForBusiness(Long businessId) {
        List<UserRole> userRoles = ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserRoles();

        if (userRoles.stream().noneMatch(userRole -> {
            if (UserRole.RoleType.BUSINESS.equals(userRole.getRoleType())) {

                return businessId.equals(userRole.getEntityId());

            }
            return true;
        })) {

            List errors = new ArrayList();
            errors.add(ValidationError.builder().field("businessId").message("Not Authorized").build());
            throw new ValidationException(errors, "User is not authorized to see information for business " + businessId);

        }
    }
}
