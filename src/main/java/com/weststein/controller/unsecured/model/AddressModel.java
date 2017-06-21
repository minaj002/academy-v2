package com.weststein.controller.unsecured.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class AddressModel {

    @NotEmpty
    private String line1;
    private String line2;
    @NotEmpty
    private String postalCode;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;

}
