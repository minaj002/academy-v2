package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ViewStatementHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;

    @Autowired
    private StatementCacheHelper cacheTest;

    public ViewStatementModel handle(String id, LocalDateTime from, LocalDateTime to, int size, int page) {

        AccountAPIv2ViewStatement res = cacheTest.callPfs(id, from, to, true);
        ViewStatementModel model = objectMapper.map(res.getViewStatement(), ViewStatementModel.class);

        applyPaging(model.getTransactions(), from, to, size, page);

        return model;
    }

    private void applyPaging(List<TransactionModel> transactions, LocalDateTime from, LocalDateTime to, int size, int page) {

        transactions.stream().filter(transaction -> transaction.getDate().isAfter(from)&&transaction.getDate().isAfter(to));

    }



}
