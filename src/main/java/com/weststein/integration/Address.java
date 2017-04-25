package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {

    @JsonProperty("line_1")
    private String line1;
    @JsonProperty("line_2")
    private String line2;
    @JsonProperty("postal_code")
    private String postalCode;
    private String city;
    private String country;
    private String state;

}
