package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositToCard {

    @JacksonXmlProperty(localName = "Cardholderid")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "Amount")
    private BigDecimal amount;
    @JacksonXmlProperty(localName = "TransactionType")
    private String transactionType;
    @JacksonXmlProperty(localName = "CurrencyCode")
    private String currencyCode;
    @JacksonXmlProperty(localName = "SettlementAmount")
    private BigDecimal settlementAmount;
    @JacksonXmlProperty(localName = "SettlementCurrencyCode")
    private String settlementCurrencyCode;
    @JacksonXmlProperty(localName = "TransactionDescription")
    private String transactionDescription;
    @JacksonXmlProperty(localName = "TerminalOwner")
    private String terminalOwner;
    @JacksonXmlProperty(localName = "TerminalLocation")
    private String terminalLocation;
    @JacksonXmlProperty(localName = "TerminalCity")
    private String terminalCity;
    @JacksonXmlProperty(localName = "TerminalState")
    private String terminalState;
    @JacksonXmlProperty(localName = "TerminalID")
    private String terminalID;
    @JacksonXmlProperty(localName = "Country")
    private String country;
    @JacksonXmlProperty(localName = "TransactionFlatFee")
    private String transactionFlatFee;
    @JacksonXmlProperty(localName = "FeeDescription")
    private String feeDescription;
    @JacksonXmlProperty(localName = "DirectFee")
    private String directFee;
    @JacksonXmlProperty(localName = "VoucherCode")
    private String voucherCode;

}
