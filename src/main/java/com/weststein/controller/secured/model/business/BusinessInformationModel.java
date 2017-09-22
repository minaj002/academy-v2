package com.weststein.controller.secured.model.business;

import com.weststein.controller.secured.model.CardholderIdModel;
import com.weststein.repository.Address;
import com.weststein.repository.CardholderId;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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