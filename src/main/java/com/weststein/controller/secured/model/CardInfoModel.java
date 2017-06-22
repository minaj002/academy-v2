package com.weststein.controller.secured.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardInfoModel {

    private String client;
    private String accountBaseCurrency;
    private String cardType;
    private String accountNumber;
    private String cardStatus;
    private Integer pinTriesExceeded;
    private Integer badPinTries;
    private String expirationDate;
    private BigDecimal availBal;
    private BigDecimal ledgerBal;
    private String distributorCode;
    private BigDecimal loadAmount;
    private String companyName;
    private String cardStyle;
    private String deliveryType;
    private String bic;
    private String iban;
    private String phoneCardNumber;

}
