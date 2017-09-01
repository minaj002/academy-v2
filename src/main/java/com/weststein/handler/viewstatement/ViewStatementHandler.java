package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
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
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private StatementCacheHelper statementCacheHelper;

    public ViewStatementModel handle(Long id, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {

        CardholderId cardholderId = cardholderIdRepository.findOne(id);
        AccountAPIv2ViewStatement res = statementCacheHelper.callPfs(cardholderId.getCardholderId(), from, to);
        ViewStatementModel model = objectMapper.map(res.getViewStatement(), ViewStatementModel.class);
        int totalTransactions =model.getTransactions().size();
        model.setTransactions(applyPaging(model.getTransactions(), from, to, type, size, page));
        model.setPage(page);
        model.setSize(size);
        model.setTotalTransactions(totalTransactions);
        return model;
    }

    private List<TransactionModel> applyPaging(List<TransactionModel> transactions, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {

        return transactions.stream()
                .filter(transaction -> !transaction.getDate().isBefore(from) && !transaction.getDate().isAfter(to))
                .filter(transaction -> TransactionType.ALL.equals(type) || type.equals(transaction.getTransactionType()))
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .skip((page-1)*size)
                .limit(size)
                .collect(Collectors.toList());
    }

}
