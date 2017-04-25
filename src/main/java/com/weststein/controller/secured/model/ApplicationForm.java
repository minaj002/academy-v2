package com.weststein.controller.secured.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weststein.configuration.CustomDeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    @NotEmpty
    @Length(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z `'-.]+")
    @ApiModelProperty(position = 1, required = true )
    private String firstName;
    @NotEmpty
    @Length(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z `'-.]+")
    @ApiModelProperty(position = 2)
    private String secondName;
    @NotEmpty
    @Length(min = 7, max = 16)
    @Pattern(regexp ="[0-9 |+]+")
    @ApiModelProperty(position = 3)
    private String mobilePhone;
    @NotEmpty
    @Length(min = 1, max = 255)
    @Email
    @ApiModelProperty(position = 4)
    private String email;
    @DateTimeFormat(pattern = "ddMMyyyy")
    @ApiModelProperty(position = 5, example = "12032001")
    @JsonDeserialize(using= CustomDeSerializer.class)
//    @JsonSerialize(using= CustomSerializer.class)
    private LocalDate dateOfBirth;
    @NotEmpty
    @Length(min = 2, max = 2)
    @Pattern(regexp = "[a-zA-Z]+")
    @ApiModelProperty(position = 6)
    private String country;
    @NotEmpty
    @Length(min = 1, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9 `'/()-]+")
    @ApiModelProperty(position = 7)
    private String city;
    @NotEmpty
    @Length(min = 1, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9 ()/-]+")
    @ApiModelProperty(position = 8)
    private String postalCode;
    @NotEmpty
    @Length(min = 1, max = 30)
    @Pattern(regexp ="[a-zA-Z0-9 .,&`'/()-]+")
    @ApiModelProperty(position = 9)
    private String address;
    @NotEmpty
    @Length(min = 1, max = 255)
    @Pattern(regexp ="[a-zA-Z0-9 ,.`'?@#-]+")
    @ApiModelProperty(position = 11)
    private String securityQuestion1;
    @NotEmpty
    @Length(min = 1, max = 255)
    @Pattern(regexp ="[a-zA-Z0-9 ,.`'?@#-]+")
    @ApiModelProperty(position = 12)
    private String securityQuestion2;
    @NotEmpty
    @Length(min = 1, max = 255)
    @Pattern(regexp ="[a-zA-Z0-9 ,.`'?@#-]+")
    @ApiModelProperty(position = 13)
    private String answer1;
    @NotEmpty
    @Length(min = 1, max = 255)
    @Pattern(regexp ="[a-zA-Z0-9 ,.`'?@#-]+")
    @ApiModelProperty(position = 14)
    private String answer2;

}
