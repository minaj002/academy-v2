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
        template.query("Username", "weststeinapiuser");
        template.query("Password", "li*ade3ASD");

        LocalDateTime dateTime = LocalDateTime.now();
        int timeStamp = dateTime.getDayOfYear() + dateTime.getHour() + dateTime.getMinute() + dateTime.getSecond();
        template.query("MessageID", nextSessionId());
    }

    public String nextSessionId() {
        return new BigInteger(130, random).toString(36);
    }
}