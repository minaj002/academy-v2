package com.weststein.controller.unsecured.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weststein.configuration.LocalDateDeSerializer;
import com.weststein.repository.UserInformation;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ApplicationModel {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @JsonDeserialize(using= LocalDateDeSerializer.class)
    private LocalDate dateOfBirth;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;
    private String phoneVerified;
    @NotNull
    private AddressModel address;
    @NotNull
    private UserInformation.Gender gender;
    @NotNull
    private Boolean agree;
    @NotNull
    private UserInformation.Language language;
}