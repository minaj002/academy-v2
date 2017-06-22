package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountAPIv2UpdateCard {

    @JacksonXmlProperty(localName = "ErrorCode")
    private String errorCode;
    @JacksonXmlProperty(localName = "Description")
    private String description;

}
