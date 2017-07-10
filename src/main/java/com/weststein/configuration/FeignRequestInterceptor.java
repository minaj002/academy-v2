package com.weststein.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

//    @Value("${solaris.token}")
    private String token;

    private SecureRandom random = new SecureRandom();

    @Override
    public void apply(RequestTemplate template) {

        switch(template.url()) {
            case "Process" :
                template.query("Username", "weststeinapiuser");
                template.query("Password", "li*ade3ASD");
                template.query("MessageID", nextSessionId());
                return;
            default:
                template.query("APIKey", "ebb0baaff3d1b57519dc1f4c705c60661cea0a2e");
                template.query("Command", "SendOne");
                template.query("Sender", "WestStein");
        }

    }

    public String nextSessionId() {
        return new BigInteger(130, random).toString(36);
    }
}