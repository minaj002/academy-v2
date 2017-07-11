package com.weststein.integration.sms;

public class SmsResourceFallback implements SmsResource {

    @Override
    public SmsResponse send(String phoneNumber, String message) {
        return null;
    }
}

