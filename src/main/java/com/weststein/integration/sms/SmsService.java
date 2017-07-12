package com.weststein.integration.sms;

public interface SmsService {

    void send(String number, String sms, String language);

}
