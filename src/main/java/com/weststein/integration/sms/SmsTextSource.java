package com.weststein.integration.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;

@Configuration
@PropertySource("classpath:/sms/sms.properties")
public class SmsTextSource {

    @Autowired
    private Environment env;

    public String getSms(String language) {
        try {
            return new String(env.getProperty("confirm.phone." + language.toLowerCase()).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {


        }
        return null;
    }

}
