package com.weststein.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.weststein.integration.request.ViewStatement;
import com.weststein.integration.response.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public class PPFServiceTest {


    @Test
    public void testSerializeViewStatement() {

        XmlMapper mapper = new XmlMapper();
        ViewStatement object = new ViewStatement();
        object.setCardholderId("400000557017");
        object.setEndDate("2016-12-03");
        object.setStartDate("2016-01-01");
        object.setViewStyle("Y");
        try {
            String res = mapper.writeValueAsString(object);
            assertEquals("<ViewStatement><Cardholderid>400000557017</Cardholderid>" +
                "<StartDate>2016-01-01</StartDate><EndDate>2016-12-03</EndDate>" +
                "<ViewStyle>Y</ViewStyle></ViewStatement>", res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerializeViewStatementResp() {

        XmlMapper mapper = new XmlMapper();
        ViewStatementResponse object = new ViewStatementResponse();
        CardholderStatementDetails cardHD = new CardholderStatementDetails();
        CardPan cardPan = new CardPan();
        cardPan.setCurrency("EUR");
        List<CardAccount> cardAccounts = new ArrayList<>();
        CardAccount account1 = new CardAccount();
        cardAccounts.add(account1);
        CardAccount account2 = new CardAccount();
        cardAccounts.add(account2);
        cardPan.setCardAccount(cardAccounts);
        cardHD.setCardpan(cardPan);
        object.setCardholderstatementdetails(cardHD);
        try {
            String res = mapper.writeValueAsString(object);
            assertEquals("<ViewStatementResponse><cardholderstatementdetails><cardpan><currency>EUR</currency><account/><startdate/><enddate/><reportdate/><cardaccount/><cardaccount/></cardpan><RecCnt/></cardholderstatementdetails></ViewStatementResponse>", res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeserializeView() throws IOException {

        XmlMapper mapper = new XmlMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"));
        File file = new File("src/test/resources/statement.xml");
        AccountAPIv2ViewStatement statement = mapper.readValue(file, AccountAPIv2ViewStatement.class);

        assertTrue(statement.getViewStatement().getCardholderstatementdetails().getCardpan().getCardAccount().size()>1);

        Map<String, Set<String>> codeDescriptionMap = new HashMap<>();

        statement.getViewStatement().getCardholderstatementdetails().getCardpan().getCardAccount().forEach(cardAccount -> {
            cardAccount.getTransaction().forEach(transaction -> {
                if(codeDescriptionMap.containsKey(transaction.getTransactionType())) {
                    codeDescriptionMap.get(transaction.getTransactionType()).add(transaction.getDescription());
                } else {
                    Set<String> descriptionSet = new HashSet<>();
                    descriptionSet.add(transaction.getDescription());
                    codeDescriptionMap.put(transaction.getTransactionType(), descriptionSet);
                }
            });
        });
        codeDescriptionMap.forEach((type, set)-> {
            System.out.println(type+ " " + set);
        });
    }

}