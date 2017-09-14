package com.weststein.controller.secured.model.business;

import com.weststein.repository.business.CardType;
import com.weststein.repository.business.CardUse;
import com.weststein.repository.CardholderCategory;
import com.weststein.repository.CurrencyEnum;
import lombok.Data;

@Data
public class CardIbanModel {

    private String explanationOfPurpose;
    private CardholderCategory holders;
    private String otherHoldersExplanation;
    private String holdersLocation;
    private CardUse use;
    private String otherCardType;
    private CurrencyEnum currency;
    private Integer numberOfCards;
    private CardType type;
    private String natureOfExpectedTransactions;
    private String fundsOrigin;
    private String flowOfFunds;

}
