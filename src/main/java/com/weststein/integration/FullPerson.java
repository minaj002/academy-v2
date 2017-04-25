package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FullPerson extends Person{

    private String email;
    @JsonProperty("mobile_number")
    private String mobileNumber;
    @JsonProperty("birth_date")
    private String birthDate;
    @JsonProperty("birth_country")
    private String birthCountry;
    @JsonProperty("birth_city")
    private String birthCity;
    private String nationality;
    @JsonProperty("employment_status")
    private String employmentStatus;
    @JsonProperty("job_title")
    private String jobTitle;
    private Address address;
    @JsonProperty("contact_address")
    private Address contactAddress;
    @JsonProperty("tax_information")
    private TaxInformation taxInformation;
}
