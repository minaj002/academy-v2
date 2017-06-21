package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssueResponse {

    @JacksonXmlProperty(localName = "Cardholderid")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "AvailableBalance")
    private BigDecimal availableBalance;
    @JacksonXmlProperty(localName = "LedgerBalance")
    private BigDecimal ledgerBalance;

}
