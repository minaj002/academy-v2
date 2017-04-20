package com.weststein.security;



import com.weststein.security.model.entity.Role;
import com.weststein.security.model.entity.User;
import com.weststein.security.model.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserService {

    public Optional<User> getByUsername(String username){

        if("admin".equals(username)){
            ArrayList<UserRole> roles = new ArrayList<UserRole>();
            roles.add(UserRole.builder().id(1l).role(Role.ADMIN).build());
            return Optional.of(User.builder().id(1l).roles(roles).username("admin").password("$2a$10$GH8WZU4/ARexbRWhd0.7zO5LdwLSgDlmfuhJTtn1YKs64n6sRrEA.").build());
        } else {
            return Optional.empty();
        }
    }
}
