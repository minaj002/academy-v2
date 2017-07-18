package com.weststein.controller.secured.model;

import lombok.Data;

import java.util.List;

@Data
public class ViewStatementModel {

    private String account;
    private String startDate;
    private String endDate;
    private List<TransactionModel> transactions;

}