package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.ViewStatement;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ViewStatementHandler {
    private static String DATE_FORMAT = "yyyy-dd-MM";

    @Autowired
    private PPFService<ViewStatement, AccountAPIv2ViewStatement> ppfService;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public ViewStatementModel handle(String id, LocalDate start, LocalDate end) {
        ViewStatement object = new ViewStatement();
        object.setCardholderId(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        object.setEndDate(formatter.format(end));
        object.setStartDate(formatter.format(start));
        object.setViewStyle("Y");
        AccountAPIv2ViewStatement res = ppfService.get(object, AccountAPIv2ViewStatement.class);

        return objectMapper.map(res.getViewStatement(), ViewStatementModel.class);
    }

}
