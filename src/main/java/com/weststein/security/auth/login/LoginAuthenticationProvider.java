package com.weststein.security.auth.login;

import com.weststein.repository.UserCredentials;
import com.weststein.security.UserService;
import com.weststein.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;
    private final UserService userService;
    @Value("${temporary.block.minutes}")
    private int temporaryBlockMinutes;

    @Autowired
    public LoginAuthenticationProvider(final UserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserCredentials user = userService.getByUsername(username).orElseThrow(() -> new BadCredentialsException("Authentication Failed. Username or Password not valid."));

        if (!encoder.matches(password, user.getPassword())) {

            if (user.getLoginAttempt() >= 4) {
                user.setStatus(UserCredentials.Status.TEMPORARY_BLOCKED);
                user.setBlockedAt(LocalDateTime.now());
                user.setLoginAttempt(0);
                userService.update(user);
                throw new BadCredentialsException("Your account is temporary blocked. Please try to login after " +(LocalDateTime.now().getMinute() - user.getBlockedAt().getMinute()) + " minutes");

            } else if(UserCredentials.Status.TEMPORARY_BLOCKED.equals(user.getStatus())&&user.getBlockedAt().plusMinutes(temporaryBlockMinutes).isBefore(LocalDateTime.now())) {
                throw new BadCredentialsException("Your account is temporary blocked. Please try to login after " +(LocalDateTime.now().getMinute() - user.getBlockedAt().getMinute()) + " minutes");

            } else if(UserCredentials.Status.TEMPORARY_BLOCKED.equals(user.getStatus())) {
                user.setStatus(UserCredentials.Status.ACTIVE);
                user.setLoginAttempt(user.getLoginAttempt()+1);
                userService.update(user);
            }else if(UserCredentials.Status.ACTIVE.equals(user.getStatus())) {
                user.setLoginAttempt(user.getLoginAttempt()+1);
                userService.update(user);
            }
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (UserCredentials.Status.BLOCKED.equals(user.getStatus())) {
            throw new BadCredentialsException("Your account is blocked.");
        }

        if (UserCredentials.Status.TEMPORARY_BLOCKED.equals(user.getStatus())) {
            if (user.getBlockedAt().plusMinutes(temporaryBlockMinutes).isAfter(LocalDateTime.now())) {
                throw new BadCredentialsException("Your account is temporary blocked. Please try to login after " +(user.getBlockedAt().plusMinutes(temporaryBlockMinutes).get(ChronoField.MINUTE_OF_DAY) - LocalDateTime.now().get(ChronoField.MINUTE_OF_DAY)) + " minutes");
            } else {
                user.setStatus(UserCredentials.Status.ACTIVE);
                userService.update(user);
            }
        } else {
            user.setLoginAttempt(0);
            userService.update(user);
        }

        if (user.getRoles() == null) { throw new InsufficientAuthenticationException("User has no roles assigned");}


        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getEmail(), authorities, user.getRoles());

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
