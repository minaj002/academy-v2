package com.weststein.controller.secured.business.model.business;

import com.weststein.controller.unsecured.model.AddressModel;
import com.weststein.repository.business.CompanyInformation;
import com.weststein.repository.Title;
import lombok.Data;

@Data
public class CompanyInformationModel {

    private String legalName;
    private String tradingName;
    private CompanyInformation.LegalStatus legalStatus;
    private AddressModel registeredAddress;
    private String principalPlaceOfBusiness;
    private String landLinePhone;
    private String mobilePhone;
    private Title contactTitle;
    private String firstName;
    private String lastName;
    private String contactEmail;
    private String contactPhone;
    private String dateOfIncorporation;
    private String registrationNumber;
    private String taxNumber;
    private String vatNumber;
    private boolean regulatedByAuthority;
    private String regulationAuthorityName;
    private String licenceNumberGrantedByAuthority;


}
