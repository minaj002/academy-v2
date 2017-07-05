package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountAPIv2CardInfo extends AccountAPIv2 {

    @JacksonXmlProperty(localName = "CardInquiry")
    private CardInquiryResponse cardInquiry;

}
