package com.weststein.security.auth.login;

import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserRole;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginAuthenticationProviderTest {

    @InjectMocks
    private LoginAuthenticationProvider provider;
    @Mock
    private BCryptPasswordEncoder encoder;
    @Mock
    private UserService userService;
    @Mock
    private Authentication auth;

    @Before
    public void setup() {
        when(auth.getPrincipal()).thenReturn("principal");
        when(auth.getCredentials()).thenReturn("credentials");
        ReflectionTestUtils.setField(provider, "temporaryBlockMinutes", 30);
    }

    @Test
    public void temporaryBlocked() throws Exception {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.TEMPORARY_BLOCKED);
        user.setBlockedAt(LocalDateTime.now().minusMinutes(10));
        user.setEmail("principal");
        user.setLoginAttempt(0);
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        try {
            provider.authenticate(auth);
            fail();
        } catch (BadCredentialsException e) {
            assertEquals("Your account is temporary blocked. Please try to login after 20 minutes", e.getMessage());
        }
    }
    @Test
    public void temporaryBlockedButTimePassed() {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.TEMPORARY_BLOCKED);
        user.setBlockedAt(LocalDateTime.now().minusMinutes(31));
        user.setEmail("principal");
        user.setLoginAttempt(0);
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        provider.authenticate(auth);
        ArgumentCaptor<UserCredentials> capture = ArgumentCaptor.forClass(UserCredentials.class);
        verify(userService).update(capture.capture());
        assertEquals(capture.getValue().getLoginAttempt(), Integer.valueOf(0));
        assertEquals(capture.getValue().getStatus(), UserCredentials.Status.ACTIVE);

    }
    @Test
    public void blocked() {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.BLOCKED);
        user.setBlockedAt(LocalDateTime.now().minusMinutes(10));
        user.setEmail("principal");
        user.setLoginAttempt(0);
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        try {
            provider.authenticate(auth);
            fail();
        } catch (BadCredentialsException e) {
            assertEquals("Your account is blocked.", e.getMessage());
        }
    }

    @Test
    public void badPassword1Time() throws Exception {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.ACTIVE);
        user.setEmail("principal");
        user.setLoginAttempt(0);
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(false);
        try {
            provider.authenticate(auth);
            fail();
        } catch (BadCredentialsException e) {
            assertEquals("Authentication Failed. Username or Password not valid.", e.getMessage());
        }
        ArgumentCaptor<UserCredentials> capture = ArgumentCaptor.forClass(UserCredentials.class);
        verify(userService).update(capture.capture());
        assertEquals(capture.getValue().getLoginAttempt(), Integer.valueOf(1));
    }

    @Test
    public void badPassword5Time() throws Exception {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.ACTIVE);
        user.setEmail("principal");
        user.setLoginAttempt(4);
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(false);


        try {
            provider.authenticate(auth);
            fail();
        } catch (BadCredentialsException e) {
            assertEquals("Your account is temporary blocked. Please try to login after 0 minutes", e.getMessage());
        }
        ArgumentCaptor<UserCredentials> capture = ArgumentCaptor.forClass(UserCredentials.class);
        verify(userService).update(capture.capture());
        assertEquals(capture.getValue().getLoginAttempt(), Integer.valueOf(0));
        assertEquals(capture.getValue().getStatus(), UserCredentials.Status.TEMPORARY_BLOCKED);
    }

    @Test
    public void authenticate() throws Exception {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.ACTIVE);
        user.setEmail("principal");
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        provider.authenticate(auth);

    }

    @Test
    public void authenticateAfterFail() throws Exception {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.ACTIVE);
        user.setLoginAttempt(3);
        user.setEmail("principal");
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenReturn(optionalUser);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        provider.authenticate(auth);
        ArgumentCaptor<UserCredentials> capture = ArgumentCaptor.forClass(UserCredentials.class);
        verify(userService).update(capture.capture());
        assertEquals(capture.getValue().getLoginAttempt(), Integer.valueOf(0));
        assertEquals(capture.getValue().getStatus(), UserCredentials.Status.ACTIVE);

    }

    @Test(expected = BadCredentialsException.class)
    public void badUsername() {
        UserCredentials user = new UserCredentials();
        user.setStatus(UserCredentials.Status.ACTIVE);
        user.setEmail("principal");
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole(Role.OWNER);
        roles.add(role);
        user.setRoles(roles);
        Optional<UserCredentials> optionalUser = Optional.of(user);
        when(userService.getByUsername("principal")).thenThrow(BadCredentialsException.class);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        provider.authenticate(auth);

    }

}