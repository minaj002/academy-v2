package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public abstract class AccountAPIv2 {

    @JacksonXmlProperty(localName = "ErrorCode")
    private String errorCode;
    @JacksonXmlProperty(localName = "Description")
    private String description;
}
