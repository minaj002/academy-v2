package com.weststein.security.model;

import com.weststein.repository.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;


public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;
    private List<UserRole> userRoles;

    private UserContext(String username, List<GrantedAuthority> authorities,  List<UserRole> userRoles) {
        this.username = username;
        this.authorities = authorities;
        this.userRoles = userRoles;
    }
    
    public static UserContext create(String username, List<GrantedAuthority> authorities, List<UserRole> userRoles) {

        if (StringUtils.isEmpty(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities, userRoles);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
