package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ViewStatementCsvHandler extends AbstractViewStatementHandler<ByteArrayOutputStream> {

    @Override
    public ByteArrayOutputStream handle(Long id, Long cardholderId, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {
        ViewStatementModel statement = getStatement(cardholderId, from, to, type, size, page);
        CSVPrinter csvFilePrinter;
        ByteArrayOutputStream res = new ByteArrayOutputStream();

        Writer out = new BufferedWriter(new OutputStreamWriter(res));
        try {
            csvFilePrinter = new CSVPrinter(out, CSVFormat.EXCEL);

            csvFilePrinter.printRecord("Date", "Type", "Beneficiary", "Details", "Amount", "Balance After", "Status");
            for (TransactionModel transaction : statement.getTransactions()) {
                csvFilePrinter.printRecord(transaction.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        transaction.getTransactionType(), transaction.getBeneficiary(),
                        transaction.getDetails(), NumberFormat.getCurrencyInstance().format(transaction.getAmount()),
                        NumberFormat.getCurrencyInstance().format(transaction.getBalanceAfter()), transaction.getStatus());
            }
            csvFilePrinter.flush();
            csvFilePrinter.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
