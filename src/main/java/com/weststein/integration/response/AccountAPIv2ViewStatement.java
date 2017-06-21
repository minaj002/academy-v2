package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountAPIv2ViewStatement {

    @JacksonXmlProperty(localName = "ErrorCode")
    private String errorCode;
    @JacksonXmlProperty(localName = "Description")
    private String description;
    @JacksonXmlProperty(localName = "ViewStatement")
    private ViewStatementResponse viewStatement;

}
