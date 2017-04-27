package com.weststein.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TaxInformation {

    @Id
    private Long id;
    private String taxAssessment;
    private String maritalStatus;

}
