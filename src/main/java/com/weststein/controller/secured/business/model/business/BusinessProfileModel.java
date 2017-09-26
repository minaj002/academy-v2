package com.weststein.controller.secured.business.model.business;

import lombok.Data;

import java.util.List;

@Data
public class BusinessProfileModel {

    private String url;
    private String ipAddress;
    private String industry;
    private String descriptionOfBusiness;
    private List<String> countriesOfOperation;
    private String detailsOfTargetMarket;
    private boolean affiliatesSellProducts;
    private String purposeOfRelationshipsWithAffiliates;

}

