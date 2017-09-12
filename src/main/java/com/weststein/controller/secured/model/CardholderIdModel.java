package com.weststein.controller.secured.model;

import lombok.Data;

@Data
public class CardholderIdModel {

    private Long id;
    private String cardholderId;
    private String iban;

}
