package com.weststein.security;


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

    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private JwtTokenFactory tokenFactory;

    public String getCurrentUser() {
        return ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Optional<UserCredentials> getByUsername(String username) {
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmail(username).get();
        return Optional.of(credentials);
    }

    public String startSession() {
        UserContext context = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtToken accessToken = tokenFactory.createAccessJwtToken(context);
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmail(context.getUsername()).get();
        credentials.setToken(accessToken.getToken());
        userCredentialRepository.save(credentials);
        return accessToken.getToken();
    }

    public void isAuthorizedForCardHolder(String cardholderId) {
        List<UserRole> usersCardholdersIds = ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserRoles();

        if (usersCardholdersIds.stream().noneMatch(userRole -> {
            if (Role.PRIVATE.equals(userRole.getRole())) {
                CardholderId cardholder = cardholderIdRepository.findOne(userRole.getEntityId());
                return cardholder.getCardholderId().equals(cardholderId);

            }
            return true;
            // TODO: JM verify id for business accounts
        })) {

            List errors = new ArrayList();
            errors.add(ValidationError.builder().field("cardholderId").message("Not Authorized").build());
            throw new ValidationException(errors, "User is not authorized to see information for cardholder " + cardholderId);

        }
    }
}
