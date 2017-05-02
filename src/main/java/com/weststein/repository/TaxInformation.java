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
public class TaxInformation {

    public enum TaxAssessment {
        NONE, SEPARATE, JOINT
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, UNKNOWN
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private TaxAssessment taxAssessment;
    private MaritalStatus maritalStatus;

}
