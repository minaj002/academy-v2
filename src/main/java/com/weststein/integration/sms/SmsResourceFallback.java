package com.weststein.integration.sms;

public class SmsResourceFallback implements SmsResource {


    @Override
    public String get(String phoneNumber, String message) {
        return null;
    }
}

