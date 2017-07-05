package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardInquiry {

    @JacksonXmlProperty(localName = "Cardholderid")
    private String cardholderId;

}
