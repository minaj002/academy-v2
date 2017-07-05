package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountAPIv2CardIssue extends AccountAPIv2 {

    @JacksonXmlProperty(localName = "CardIssue")
    private CardIssueResponse cardIssue;
    @JacksonXmlProperty(localName = "ReferenceID")
    private String referenceID;

}
