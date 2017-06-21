package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class CardHolderResponse {


    @JacksonXmlProperty(localName = "CardHolderID")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "CardNumber")
    private String cardNumber;
    @JacksonXmlProperty(localName = "FirstName")
    private String firstName;
    @JacksonXmlProperty(localName = "LastName")
    private String lastName;
    @JacksonXmlProperty(localName = "MiddleInitial")
    private String middleInitial;
    @JacksonXmlProperty(localName = "Phone")
    private String phone;
    @JacksonXmlProperty(localName = "Phone2")
    private String phone2;
    @JacksonXmlProperty(localName = "EmailAddr")
    private String email;
    @JacksonXmlProperty(localName = "DOB")
    private String dob;
    @JacksonXmlProperty(localName = "Nationality")
    private String nationality;
    @JacksonXmlProperty(localName = "CountryCode")
    private String countryCode;
    @JacksonXmlProperty(localName = "CountryCode2")
    private String countryCode2;
    @JacksonXmlProperty(localName = "CountryName")
    private String countryName;
    @JacksonXmlProperty(localName = "CountryName2")
    private String countryName2;
    @JacksonXmlProperty(localName = "CountyName")
    private String countyName;
    @JacksonXmlProperty(localName = "CountyName2")
    private String countyName2;
    @JacksonXmlProperty(localName = "City")
    private String city;
    @JacksonXmlProperty(localName = "City2")
    private String city2;
    @JacksonXmlProperty(localName = "State")
    private String state;
    @JacksonXmlProperty(localName = "State2")
    private String state2;
    @JacksonXmlProperty(localName = "Zip")
    private String zipCode;
    @JacksonXmlProperty(localName = "Zip2")
    private String zipCode2;
    @JacksonXmlProperty(localName = "Address1")
    private String address1;
    @JacksonXmlProperty(localName = "Address2")
    private String address2;
    @JacksonXmlProperty(localName = "Address3")
    private String address3;
    @JacksonXmlProperty(localName = "Address4")
    private String address4;
    @JacksonXmlProperty(localName = "SecondaryAddress1")
    private String secondaryAddress1;
    @JacksonXmlProperty(localName = "SecondaryAddress2")
    private String secondaryAddress2;
    @JacksonXmlProperty(localName = "SecondaryAddress3")
    private String secondaryAddress3;
    @JacksonXmlProperty(localName = "SecondaryAddress4")
    private String secondaryAddress4;
    @JacksonXmlProperty(localName = "UserDefinedField1")
    private String userDefinedField1;
    @JacksonXmlProperty(localName = "UserDefinedField2")
    private String userDefinedField2;
    @JacksonXmlProperty(localName = "UserDefinedField3")
    private String userDefinedField3;
    @JacksonXmlProperty(localName = "UserDefinedField4")
    private String userDefinedField4;
    @JacksonXmlProperty(localName = "CardStyle")
    private String cardStyle;
    @JacksonXmlProperty(localName = "SSN")
    private String ssn;
    @JacksonXmlProperty(localName = "SecurityField1")
    private String securityField1;
    @JacksonXmlProperty(localName = "SecurityField2")
    private String securityField2;
    @JacksonXmlProperty(localName = "SecurityField3")
    private String securityField3;
    @JacksonXmlProperty(localName = "SecurityField4")
    private String securityField4;
    @JacksonXmlProperty(localName = "EmbossName")
    private String embossName;
    @JacksonXmlProperty(localName = "DocumentType")
    private String documentType;
    @JacksonXmlProperty(localName = "DocumentNumber")
    private String documentNumber;
    @JacksonXmlProperty(localName = "DocumentExpiryDate")
    private String documentExpiryDate;
    @JacksonXmlProperty(localName = "CountryOfIssuance")
    private String countryOfIssuance;

}
