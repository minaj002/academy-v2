package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JacksonXmlRootElement(localName = "Cardtocard")
public class CardToCard {

    @JacksonXmlProperty(localName = "Cardholderid")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "Amount")
    private BigDecimal amount;
    @JacksonXmlProperty(localName = "CurrencyCode")
    private String currencyCode;
    @JacksonXmlProperty(localName = "CardNumberTo")
    private String cardNumberTo;
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
    @JacksonXmlProperty(localName = "Description")
    private String description;
    @JacksonXmlProperty(localName = "SettlementCurrencyCode")
    private String settlementCurrencyCode;
    @JacksonXmlProperty(localName = "DirectFee")
    private String directFee;

}
