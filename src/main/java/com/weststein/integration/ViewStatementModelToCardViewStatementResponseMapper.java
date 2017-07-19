package com.weststein.integration;

import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.integration.response.ViewStatementResponse;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewStatementModelToCardViewStatementResponseMapper extends ObjectMapperConfiguration<ViewStatementModel, ViewStatementResponse> {

    @Override
    public Class<ViewStatementModel> getA() {
        return ViewStatementModel.class;
    }

    @Override
    public Class<ViewStatementResponse> getB() {
        return ViewStatementResponse.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<ViewStatementModel, ViewStatementResponse> builder) {

        builder
                .field("startDate", "cardholderstatementdetails.cardpan.startdate")
                .field("endDate", "cardholderstatementdetails.cardpan.enddate")
                .field("account", "cardholderstatementdetails.cardpan.account")
                .customize(new TransactionMapper())
                .byDefault();

    }

    private class TransactionMapper extends CustomMapper<ViewStatementModel, ViewStatementResponse> {

        @Override
        public void mapBtoA(ViewStatementResponse response, ViewStatementModel model, MappingContext context) {

            List<TransactionModel> transactions = new ArrayList<>();

            response.getCardholderstatementdetails().getCardpan()
                    .getCardAccount()
                    .forEach(account -> transactions.add(TransactionModel.builder()
                            .amount(account.getTransaction().get(0).getAmount())
                            .date(LocalDateTime.parse(account.getTransaction().get(0).getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a")))
                            .details(account.getTransaction().get(0).getDescription())
                            .balanceAfter(account.getTransaction().get(0).getAvailableBalance())
                            .beneficiary(account.getTransaction().get(0).getClientId())
                            .transactionType(account.getTransaction().get(0).getTransactionType())
                            .build()));
            model.setTransactions(transactions);
        }

    }

}
