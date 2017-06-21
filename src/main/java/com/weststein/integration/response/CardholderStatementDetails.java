package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class CardholderStatementDetails {

    private CardPan cardpan;
    @JacksonXmlProperty(localName = "RecCnt")
    private String recCnt;

}
