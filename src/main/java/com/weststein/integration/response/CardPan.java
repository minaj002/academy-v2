package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class CardPan {

    private String currency;
    @JacksonXmlProperty(localName = "cardaccount")
    private CardAccount cardAccount;
    private String account;
    private String startdate;
    private String enddate;
    private String reportdate;

}
