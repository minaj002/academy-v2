package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    @JacksonXmlProperty(localName = "date")
    private String date;
    @JacksonXmlProperty(localName = "Cardholderid")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "TransactionType")
    private String transactionType;
    @JacksonXmlProperty(localName = "MTI")
    private String mti;
    @JacksonXmlProperty(localName = "mcc")
    private String mcc;
    @JacksonXmlProperty(localName = "ARN")
    private String arn;
    @JacksonXmlProperty(localName = "STN")
    private String stn;
    @JacksonXmlProperty(localName = "TermID")
    private String termId;
    @JacksonXmlProperty(localName = "AuthNum")
    private String authNum;
    @JacksonXmlProperty(localName = "RecType")
    private String recType;
    @JacksonXmlProperty(localName = "TransactionOrigin")
    private String transactionOrigin;
    private String description;
    private BigDecimal amount;
    private BigDecimal fee;
    @JacksonXmlProperty(localName = "availablebalance")
    private BigDecimal availableBalance;
    @JacksonXmlProperty(localName = "ledgerbalance")
    private BigDecimal ledgerBalance;
    @JacksonXmlProperty(localName = "issuerfee")
    private BigDecimal issuerFee;
    @JacksonXmlProperty(localName = "clientid")
    private String clientId;
    @JacksonXmlProperty(localName = "termnamelocation")
    private String termNameLocation;
    @JacksonXmlProperty(localName = "termowner")
    private String termOwner;
    @JacksonXmlProperty(localName = "termcity")
    private String termCity;
    @JacksonXmlProperty(localName = "termstate")
    private String termState;
    @JacksonXmlProperty(localName = "termcountry")
    private String termCountry;
    private BigDecimal surcharge;
    @JacksonXmlProperty(localName = "rspcode")
    private String rspCode;


}
