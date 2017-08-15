package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AccountAPIv2CardInfo extends AccountAPIv2 {

    @JacksonXmlProperty(localName = "CardInquiry")
    private CardInquiryResponse cardInquiry;

}
