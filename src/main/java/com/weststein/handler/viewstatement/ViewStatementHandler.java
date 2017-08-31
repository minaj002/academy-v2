package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ViewStatementHandler {


    @Autowired
    private OrikoObjectMapper objectMapper;

    @Autowired
    private StatementCacheHelper cacheTest;

    public ViewStatementModel handle(String id, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {

        AccountAPIv2ViewStatement res = cacheTest.callPfs(id, from, to, true);
        ViewStatementModel model = objectMapper.map(res.getViewStatement(), ViewStatementModel.class);

        model.setTransactions(applyPaging(model.getTransactions(), from, to, type, size, page));

        return model;
    }

    private List<TransactionModel> applyPaging(List<TransactionModel> transactions, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {

        return transactions.stream()
                .filter(transaction -> !transaction.getDate().isBefore(from) && !transaction.getDate().isAfter(to))
                .filter(transaction -> TransactionType.ALL.equals(type) ? true : type.equals(transaction.getTransactionType()))
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .skip((size-1)*page)
                .limit(page)
                .collect(Collectors.toList());
    }

}
