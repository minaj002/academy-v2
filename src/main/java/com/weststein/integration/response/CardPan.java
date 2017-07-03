package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class CardPan {

    private String currency;
    @JacksonXmlProperty(localName = "cardaccount")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CardAccount> cardAccount;
    private String account;
    private String startdate;
    private String enddate;
    private String reportdate;

}
