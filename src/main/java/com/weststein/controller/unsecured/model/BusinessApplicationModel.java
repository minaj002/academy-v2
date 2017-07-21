package com.weststein.controller.unsecured.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weststein.configuration.LocalDateDeSerializer;
import com.weststein.repository.Language;
import com.weststein.repository.UserInformation;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BusinessApplicationModel {

    @NotEmpty
    private String enterpriseName;
    @NotEmpty
    private String legalStatus;
    @NotNull
    private AddressModel address;
    @NotNull
    @JsonDeserialize(using= LocalDateDeSerializer.class)
    private LocalDate dateOfIncorporation;
    @NotEmpty
    private String registrationNumber;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String ownerCountry;
    @NotNull
    private String position;
    @NotNull
    private Language language;
}