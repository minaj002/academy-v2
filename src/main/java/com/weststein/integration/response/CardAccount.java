package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.weststein.integration.response.Transaction;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CardAccount {

    @JacksonXmlProperty(localName = "transaction")
    @JacksonXmlElementWrapper(localName = "transactionlist")
    private List<Transaction> transaction;

}
