package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCard {

    @JacksonXmlProperty(localName = "CardHolderID")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "FirstName")
    private String firstName;
    @JacksonXmlProperty(localName = "LastName")
    private String lastName;
    @JacksonXmlProperty(localName = "Email")
    private String email;
    @JacksonXmlProperty(localName = "DOB")
    private String dob;
    @JacksonXmlProperty(localName = "ZipCode")
    private String zipCode;
    @JacksonXmlProperty(localName = "SecurityField1")
    private String securityField1;
    @JacksonXmlProperty(localName = "SecurityField2")
    private String securityField2;

}
