package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaxInformation {

    @JsonProperty("tax_assessment")
    private String taxAssessment;
    @JsonProperty("marital_status")
    private String maritalStatus;

}
