package com.weststein.security;


import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.security.model.UserContext;
import com.weststein.security.model.token.JwtToken;
import com.weststein.security.model.token.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UserService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;
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
        Set<String> usersCardholdersIds = ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCardHolderIds();
        if (!usersCardholdersIds.contains(cardholderId)) {
            List errors = new ArrayList();
            errors.add(ValidationError.builder().field("cardholderId").message("Not Authorized").build());
            throw new ValidationException(errors, "User is not authorized to see information for cardholder " + cardholderId);
        }
    }
}
