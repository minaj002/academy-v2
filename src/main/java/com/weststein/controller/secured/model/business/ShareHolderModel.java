package com.weststein.controller.secured.model.business;

import com.weststein.controller.unsecured.model.AddressModel;
import com.weststein.repository.ShareHolderRole;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ShareHolderModel {

    private String firstName;
    private String lastName;
    private ShareHolderRole role;
    private BigDecimal percentageOwned;
    private LocalDate dateOfBirth;
    private AddressModel address;
    private String phone;
    private String email;

}
