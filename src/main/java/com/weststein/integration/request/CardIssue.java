package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class CardIssue {

    @JacksonXmlProperty(localName = "FirstName")
    private String firstName;
    @JacksonXmlProperty(localName = "LastName")
    private String lastName;
    @JacksonXmlProperty(localName = "Phone2")
    private String phone;
    @JacksonXmlProperty(localName = "Email")
    private String email;
    @JacksonXmlProperty(localName = "DOB")
    private String dob;
    @JacksonXmlProperty(localName = "CountryCode")
    private String countryCode;
    @JacksonXmlProperty(localName = "City")
    private String city;
    @JacksonXmlProperty(localName = "ZipCode")
    private String zipCode;
    @JacksonXmlProperty(localName = "Address1")
    private String address1;
    @JacksonXmlProperty(localName = "Address2")
    private String address2;
    @JacksonXmlProperty(localName = "bin")
    private String bin;
    @JacksonXmlProperty(localName = "VerifySSNOverride")
    private String verifySSNOverride;
    @JacksonXmlProperty(localName = "VerifyDOBOverride")
    private String verifyDOBOverride;
    @JacksonXmlProperty(localName = "GeoIPCheckOverride")
    private String geoIPCheckOverride;
    @JacksonXmlProperty(localName = "Userdefinedfield2")
    private String userDefinedField2;
    @JacksonXmlProperty(localName = "Userdefinedfield3")
    private String userDefinedField3;
    @JacksonXmlProperty(localName = "CardStyle")
    private String cardStyle;
    @JacksonXmlProperty(localName = "SecurityField1")
    private String securityField1;
    @JacksonXmlProperty(localName = "SecurityField2")
    private String securityField2;
    @JacksonXmlProperty(localName = "SecurityField3")
    private String securityField3;
    @JacksonXmlProperty(localName = "DistributorCode")
    private String distributorCode;

}
