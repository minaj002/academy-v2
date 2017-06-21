package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class CardInquiryResponse {

    @JacksonXmlProperty(localName = "cardinfo")
    private CardInfoResponse cardInfo;
    @JacksonXmlProperty(localName = "cardholder")
    private CardHolderResponse cardHolder;

}
