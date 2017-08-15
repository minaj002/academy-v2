package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AccountAPIv2CardIssue extends AccountAPIv2 {

    @JacksonXmlProperty(localName = "CardIssue")
    private CardIssueResponse cardIssue;
    @JacksonXmlProperty(localName = "ReferenceID")
    private String referenceID;

}
