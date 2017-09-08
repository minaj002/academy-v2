package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JacksonXmlRootElement(localName = "A33")
public class SepaPayment {

    @JacksonXmlProperty(localName = "P1")
    private String cardholderId;
    @JacksonXmlProperty(localName = "B1")
    private String beneficiary;
    @JacksonXmlProperty(localName = "S1")
    private String amount;
    @JacksonXmlProperty(localName = "S2")
    private String type;
    @JacksonXmlProperty(localName = "S3")
    private String paymentDate;
    @JacksonXmlProperty(localName = "O1")
    private String details;
    @JacksonXmlProperty(localName = "O3")
    private String iban;
    @JacksonXmlProperty(localName = "O4")
    private String bic;


}
