package com.weststein.controller.secured.model.business;

import com.weststein.repository.CurrencyEnum;
import lombok.Data;

@Data
public class BankAccountDetailsModel {

    private CurrencyEnum currency;
    private String bic;
    private String iban;
    private String sortCode;
    private String accountNumber;
    private String routingNumber;
    private String bankNameAndAddress;
    private String accountHolderName;

}
