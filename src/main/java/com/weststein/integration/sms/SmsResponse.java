package com.weststein.integration.sms;

import lombok.Data;

@Data
public class SmsResponse {
    private String messageId;
    private Integer length;
    private String unicode;
    private Integer longSMS;
    private String invalid;
    private String error;
    private String network;
}
