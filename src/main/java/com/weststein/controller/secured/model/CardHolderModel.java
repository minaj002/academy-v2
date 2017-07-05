package com.weststein.controller.secured.model;

import lombok.Data;

@Data
public class CardHolderModel {

    private String cardNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String zipCode;
    private String securityField1;
    private String securityField2;

}
