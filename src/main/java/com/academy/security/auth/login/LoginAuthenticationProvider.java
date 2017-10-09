package com.academy.security.auth.login;

import com.academy.core.domain.AcademyUser;
import com.academy.repository.AcademyUserRepository;
import com.academy.security.model.entity.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder encoder;
    private AcademyUserRepository academyUserRepository;


    @Autowired
    public LoginAuthenticationProvider(final AcademyUserRepository academyUserRepository,
                                       final PasswordEncoder encoder) {
        this.academyUserRepository = academyUserRepository;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        AcademyUser user = academyUserRepository.findByName(username);
        if (user == null) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getRoles() == null) {
            throw new InsufficientAuthenticationException("User has no roles assigned");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getName(), authorities, user.getRoles());

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
