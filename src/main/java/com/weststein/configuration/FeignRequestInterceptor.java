package com.weststein.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${pfs.user}")
    private String pfsUser;
    @Value("${pfs.password}")
    private String pfsPassword;
    @Value("${sms.key}")
    private String smsKey;

    private SecureRandom random = new SecureRandom();

    @Override
    public void apply(RequestTemplate template) {

        switch(template.url()) {
            case "Process" :
                template.query("Username", pfsUser);
                template.query("Password", pfsPassword);
                template.query("MessageID", nextMessageId());
                return;
            default:
                template.query("APIKey", smsKey);
                template.query("Command", "SendOne");
                template.query("Sender", "WestStein");
        }

    }

    public String nextMessageId() {
        return new BigInteger(130, random).toString(36);
    }
}