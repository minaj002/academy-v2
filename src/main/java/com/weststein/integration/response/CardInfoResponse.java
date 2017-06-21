package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardInfoResponse {

    @JacksonXmlProperty(localName = "Client")
    private String client;
    @JacksonXmlProperty(localName = "AccountBaseCurrency")
    private String accountBaseCurrency;
    @JacksonXmlProperty(localName = "CardType")
    private String cardType;
    @JacksonXmlProperty(localName = "AccountNumber")
    private String accountNumber;
    @JacksonXmlProperty(localName = "CardStatus")
    private String cardStatus;
    @JacksonXmlProperty(localName = "PinTriesExceeded")
    private Integer pinTriesExceeded;
    @JacksonXmlProperty(localName = "BadPinTries")
    private Integer badPinTries;
    @JacksonXmlProperty(localName = "ExpirationDate")
    private String expirationDate;
    @JacksonXmlProperty(localName = "AvailBal")
    private BigDecimal availBal;
    @JacksonXmlProperty(localName = "LedgerBal")
    private BigDecimal ledgerBal;
    @JacksonXmlProperty(localName = "DistributorCode")
    private String distributorCode;
    @JacksonXmlProperty(localName = "LoadAmount")
    private BigDecimal loadAmount;
    @JacksonXmlProperty(localName = "CompanyName")
    private String companyName;
    @JacksonXmlProperty(localName = "CardStyle")
    private String cardStyle;
    @JacksonXmlProperty(localName = "DeliveryType")
    private String deliveryType;
    @JacksonXmlProperty(localName = "Bic")
    private String bic;
    @JacksonXmlProperty(localName = "Iban")
    private String iban;
    @JacksonXmlProperty(localName = "PhonecardNumber")
    private String phoneCardNumber;

}
