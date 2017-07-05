package com.weststein.controller.secured.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SepaTransferModel {

    private String beneficiary;
    private BigDecimal amount;
    private String type;
    private String paymentDate;
    private String details;
    private String iban;
    private String bic;


}
