package com.weststein.integration;

import lombok.Data;

@Data
public class Account {

    private String id;
    private String iban;
    private String bic;
    private String type;
    private String personId;
    private Balance balance;

}
