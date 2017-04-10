package com.weststein.integration;

import lombok.Data;

@Data
public class CardIssue {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String dob;
    private String countryCode;
    private String city;
    private String zipCode;
    private String address;
    private String securityField1;
    private String securityField2;

}
