package com.weststein.controller.secured.model.business;

import com.weststein.repository.CardType;
import com.weststein.repository.CardUse;
import com.weststein.repository.CardholderCategory;
import com.weststein.repository.Currency;
import lombok.Data;

import java.util.List;

@Data
public class CardIbanModel {

    private String explanationOfPurpose;
    private CardholderCategory holders;
    private String otherHoldersExplanation;
    private String holdersLocation;
    private CardUse use;
    private String otherCardType;
    private Currency currency;
    private Integer numberOfCards;
    private CardType type;
    private String natureOfExpectedTransactions;
    private String fundsOrigin;
    private String flowOfFunds;

}
