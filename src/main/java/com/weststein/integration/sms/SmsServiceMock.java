package com.weststein.integration.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@Slf4j
public class SmsServiceMock implements SmsService {

    @Override
    public void send(String number, String sms, String language) {
        log.info("Mocking sending sms.");
    }
}