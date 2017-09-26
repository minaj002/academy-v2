package com.weststein.controller.secured.business.model.business;

import com.weststein.pdf.CurrencyEnum;
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
