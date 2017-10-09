package com.academy.security.model.entity;

import com.academy.core.domain.AcademyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;


public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;
    private List<AcademyUser.Role> userRoles;

    private UserContext(String username, List<GrantedAuthority> authorities, List<AcademyUser.Role> userRoles) {
        this.username = username;
        this.authorities = authorities;
        this.userRoles = userRoles;
    }

    public static UserContext create(String username, List<GrantedAuthority> authorities, List<AcademyUser.Role> userRoles) {

        if (StringUtils.isEmpty(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities, userRoles);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public List<AcademyUser.Role> getUserRoles() {
        return userRoles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(username);
        userRoles.forEach(role -> builder.append(", ").append(role));
        return builder.toString();
    }
}
