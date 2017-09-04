package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.handler.viewstatement.TransactionType;
import com.weststein.handler.viewstatement.ViewStatementHandler;
import com.weststein.infrastructure.MessageBean;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class ViewStatementController {

    @Autowired
    private UserService userService;
    @Autowired
    private ViewStatementHandler viewStatementHandler;
    @Autowired
    private MessageBean messageBean;

    @GetMapping("/api/view-statement/{cardHolderId}")
    @ApiOperation(value = "allows user to view his statement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<ViewStatementModel> view(@PathVariable Long cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start,
                                                    @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end,
                                                    @RequestParam TransactionType type,
                                                    @RequestParam int size,
                                                    @RequestParam int page
    ) {
        userService.isAuthorizedForCardHolder(cardHolderId);

        LocalDateTime startAsLocalDateTime = LocalDateTime.of(start, LocalTime.of(0, 0));
        LocalDateTime endAsLocalDateTime = getEndLocalDateTime(end);

        return ResponseWrapper.<ViewStatementModel>builder()
                .response(viewStatementHandler.handle(cardHolderId, startAsLocalDateTime, endAsLocalDateTime, type, size, page))
                .messages(messageBean.getMessages())
                .build();
    }

    private LocalDateTime getEndLocalDateTime(@DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end) {
        LocalDateTime endAsLocalDateTime;
        if (end == null) {
            endAsLocalDateTime = LocalDateTime.now();
        } else {
            endAsLocalDateTime = LocalDateTime.of(end, LocalTime.of(23, 59));
        }
        return endAsLocalDateTime;
    }

    @GetMapping(value = "/api/view-statement/csv/{cardHolderId}", produces = "application/csv")
    @ApiOperation(value = "allows user to download his statement as csv file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Resource viewCsv(@PathVariable Long cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start,
                            @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end,
                            @RequestParam TransactionType type,
                            @RequestParam int size,
                            @RequestParam int page
    ) throws IOException {
        userService.isAuthorizedForCardHolder(cardHolderId);

        LocalDateTime startAsLocalDateTime = LocalDateTime.of(start, LocalTime.of(0, 0));
        LocalDateTime endAsLocalDateTime = getEndLocalDateTime(end);
        ViewStatementModel statement = viewStatementHandler.handle(cardHolderId, startAsLocalDateTime, endAsLocalDateTime, type, size, page);
        CSVPrinter csvFilePrinter;
        ByteArrayOutputStream res = new ByteArrayOutputStream();

        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(res));
            csvFilePrinter = new CSVPrinter(out, CSVFormat.EXCEL);
            csvFilePrinter.printRecord("Date", "Type", "Beneficiary", "Details", "Amount", "Balance After", "Status");
            for (TransactionModel transaction : statement.getTransactions()) {
                csvFilePrinter.printRecord(transaction.getDate(),
                        transaction.getTransactionType(), transaction.getBeneficiary(),
                        transaction.getDetails(), transaction.getAmount(),
                        transaction.getBalanceAfter(), transaction.getStatus());
            }
            csvFilePrinter.flush();
            csvFilePrinter.close();
            ByteArrayResource resource = new ByteArrayResource(res.toByteArray());
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
