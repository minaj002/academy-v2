package com.weststein.controller.secured.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SepaTransferModel {

    private Long id;
    private String beneficiary;
    private BigDecimal amount;
    private String paymentDate;
    private String details;
    private String iban;
    private String bic;
    private BigDecimal predictedFee;
    private String status;


}
