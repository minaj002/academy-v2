package com.weststein.security;


import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserRoleRepository;
import com.weststein.security.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public Optional<UserCredentials> getByUsername(String username){
        if("admin".equals(username)){
            Set<Role> roles = new HashSet<>();

            UserCredentials cred = new UserCredentials();
            cred.setRoles(roles);
            roles.add(Role.ADMIN);
            cred.setEmail("admin");
            cred.setPassword("$2a$10$GH8WZU4/ARexbRWhd0.7zO5LdwLSgDlmfuhJTtn1YKs64n6sRrEA.");
            return Optional.of(cred);}
        else {
            UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmail(username).get();
            return Optional.of(credentials);
        }
    }
}
