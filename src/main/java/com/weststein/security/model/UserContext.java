package com.weststein.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;


public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;
    private Set<String> cardHolderIds;

    private UserContext(String username, List<GrantedAuthority> authorities, Set<String> cardHolderIds) {
        this.username = username;
        this.authorities = authorities;
        this.cardHolderIds = cardHolderIds;
    }
    
    public static UserContext create(String username, List<GrantedAuthority> authorities, Set<String> cardHolderIds) {

        if (StringUtils.isEmpty(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities, cardHolderIds);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Set<String> getCardHolderIds() {
        return cardHolderIds;
    }
}
