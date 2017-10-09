package com.academy.security.auth.login;

import com.academy.repository.AcademyUserRepository;
import com.academy.security.model.entity.UserContext;
import com.academy.security.model.entity.token.JwtToken;
import com.academy.security.model.entity.token.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.academy.configuration.WebSecurityConfig.HEADER_PREFIX;
import static com.academy.configuration.WebSecurityConfig.JWT_TOKEN_HEADER_PARAM;

@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenFactory tokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setHeader(JWT_TOKEN_HEADER_PARAM, HEADER_PREFIX + startSession((UserContext) authentication.getPrincipal()));
        clearAuthenticationAttributes(request);
    }

    public String startSession(UserContext userContext) {
        JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        return accessToken.getToken();
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
