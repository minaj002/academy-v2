package com.weststein.controller.secured.business.model.business;

import com.weststein.controller.secured.model.CardholderIdModel;
import com.weststein.repository.Address;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BusinessInformationModel {

    private Long id;
    private String enterpriseName;
    private String legalStatus;
    private Address address;
    private LocalDate dateOfIncorporation;
    private String registrationNumber;
    private Boolean agree;
    private LocalDateTime agreeOn;
    private List<CardholderIdModel> cardholderIds;

}