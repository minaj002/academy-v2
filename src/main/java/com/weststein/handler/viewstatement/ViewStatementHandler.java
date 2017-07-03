package com.weststein.handler.viewstatement;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.ViewStatement;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import com.weststein.integration.response.ViewStatementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ViewStatementHandler {
    private static String DATE_FORMAT = "yyyy-dd-MM";

    @Autowired
    private PPFService<ViewStatement, AccountAPIv2ViewStatement> ppfService;


    public ViewStatementResponse handle(String id, LocalDate start, LocalDate end) {
        ViewStatement object = new ViewStatement();
        object.setCardholderid(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        object.setEndDate(formatter.format(end));
//        object.setEndDate("2016-12-03");
        object.setStartDate(formatter.format(start));
//        object.setStartDate("2016-01-01");
        object.setViewStyle("Y");
        AccountAPIv2ViewStatement res = ppfService.get(object, AccountAPIv2ViewStatement.class);
        return res.getViewStatement();
    }

}
