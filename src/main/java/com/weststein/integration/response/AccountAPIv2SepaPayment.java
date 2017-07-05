package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountAPIv2SepaPayment extends AccountAPIv2 {

    @JacksonXmlProperty(localName = "ReferenceID")
    private String referenceID;

}
