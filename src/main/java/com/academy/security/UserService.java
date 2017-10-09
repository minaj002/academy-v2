package com.academy.security;

import com.academy.security.model.entity.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public String getUserName() {
        return ((UserContext)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
