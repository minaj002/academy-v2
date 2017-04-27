package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SolarisPerson {

    @JsonProperty("id")
    private String solarisId;
    private String salutation;
    private String title;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
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
    private SolarisAddress address;
    @JsonProperty("contact_address")
    private SolarisAddress contactAddress;
    @JsonProperty("tax_information")
    private SolarisTaxInformation taxInformation;

}
