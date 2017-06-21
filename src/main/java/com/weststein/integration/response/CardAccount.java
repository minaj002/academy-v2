package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.weststein.integration.response.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class CardAccount {

    @JacksonXmlProperty(localName = "transactionlist")
    private List<Transaction> transactionList;

}
