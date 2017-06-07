package com.weststein.security.auth.hook;

import com.weststein.configuration.HookSignatures;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

@Slf4j
public class HookProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;


    public HookProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
                                AuthenticationFailureHandler failureHandler) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String event = request.getHeader("Solaris-Webhook-Event-Type");

        if ("WEBHOOK-SUBSCRIPTION".equalsIgnoreCase(event)) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            return null;
        } else {
            String webHookSignature[] = request.getHeader("Solaris-Webhook-Signature").split("=");

            log.info("algorithm:" + webHookSignature[0] + ", sign:" + webHookSignature[1]);


        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "admin");
        Authentication res = this.getAuthenticationManager().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(res);
        return res;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String event = request.getHeader("Solaris-Webhook-Event-Type");
        if ("WEBHOOK-SUBSCRIPTION".equalsIgnoreCase(event)) {
            return;
        }
        successHandler.onAuthenticationSuccess(request, response, authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
