package com.weststein.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String line1;
    private String line2;
    private String postalCode;
    private String city;
    private String country;
    private String state;

}
