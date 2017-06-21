package com.weststein.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.weststein.integration.request.ViewStatement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PPFServiceTest {


    @Test
    public void testSerializeViewStatement() {

        XmlMapper mapper = new XmlMapper();
        ViewStatement object = new ViewStatement();
        object.setCardholderid("400000557017");
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


}