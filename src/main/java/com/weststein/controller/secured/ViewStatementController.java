package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.handler.viewstatement.*;
import com.weststein.infrastructure.MessageBean;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private ViewStatementPdfPersonalHandler viewStatementPdfPersonalHandler;
    @Autowired
    private ViewStatementPdfBusinessHandler viewStatementPdfBusinessHandler;
    @Autowired
    private ViewStatementCsvHandler viewStatementCsvHandler;
    @Autowired
    private MessageBean messageBean;

    @GetMapping("/api/business/{businessId}/view-statement/{cardHolderId}")
    @ApiOperation(value = "allows user to view business account statement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<ViewStatementModel> viewBusiness(@PathVariable Long businessId, @PathVariable Long cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start,
                                                            @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end,
                                                            @RequestParam TransactionType type,
                                                            @RequestParam int size,
                                                            @RequestParam int page
    ) {
        userService.isAuthorizedForCardHolder(cardHolderId);

        LocalDateTime startAsLocalDateTime = LocalDateTime.of(start, LocalTime.of(0, 0));
        LocalDateTime endAsLocalDateTime = getEndLocalDateTime(end);

        return ResponseWrapper.<ViewStatementModel>builder()
                .response(viewStatementHandler.handle(businessId, cardHolderId, startAsLocalDateTime, endAsLocalDateTime, type, size, page))
                .messages(messageBean.getMessages())
                .build();
    }

    @GetMapping(value = "/api/business/{businessId}/view-statement/pdf/{cardHolderId}", produces = "application/pdf")
    @ApiOperation(value = "allows user to download business account statement as pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Resource viewBusinessPdf(@PathVariable Long businessId, @PathVariable Long cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start,
                                    @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end,
                                    @RequestParam TransactionType type,
                                    @RequestParam int size,
                                    @RequestParam int page
    ) {

        ByteArrayOutputStream res = getByteArrayOutputStream(businessId, cardHolderId, start, end, type, size, page, viewStatementPdfBusinessHandler::handle);
        return new ByteArrayResource(res.toByteArray());
    }

    @GetMapping(value = "/api/personal/{personalId}/view-statement/pdf/{cardHolderId}", produces = "application/pdf")
    @ApiOperation(value = "allows user to download personal account statement as pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Resource viewPersonalPdf(@PathVariable Long personalId, @PathVariable Long cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start,
                                    @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end,
                                    @RequestParam TransactionType type,
                                    @RequestParam int size,
                                    @RequestParam int page
    ) {
        ByteArrayOutputStream res = getByteArrayOutputStream(personalId, cardHolderId, start, end, type, size, page, viewStatementPdfPersonalHandler::handle);
        return new ByteArrayResource(res.toByteArray());
    }

    @GetMapping(value = "/api/business/{businessId}/view-statement/csv/{cardHolderId}", produces = "application/csv")
    @ApiOperation(value = "allows user to download business account statement as csv file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Resource viewBusinessCsv(@PathVariable Long businessId, @PathVariable Long cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start,
                                    @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) LocalDate end,
                                    @RequestParam TransactionType type,
                                    @RequestParam int size,
                                    @RequestParam int page
    ) throws IOException {
        ByteArrayOutputStream res = getByteArrayOutputStream(businessId, cardHolderId, start, end, type, size, page, viewStatementCsvHandler::handle);
        return new ByteArrayResource(res.toByteArray());
    }

    private ByteArrayOutputStream getByteArrayOutputStream(Long personalId, Long cardHolderId,
                                                           LocalDate start, LocalDate end,
                                                           TransactionType type,
                                                           int size, int page,
                                                           Function7<Long, Long, LocalDateTime, LocalDateTime, TransactionType, Integer, Integer, ByteArrayOutputStream> function
    ) {

        userService.isAuthorizedForCardHolder(cardHolderId);

        LocalDateTime startAsLocalDateTime = LocalDateTime.of(start, LocalTime.of(0, 0));
        LocalDateTime endAsLocalDateTime = getEndLocalDateTime(end);
        return function.apply(personalId, cardHolderId, startAsLocalDateTime, endAsLocalDateTime, type, size, page);
    }

    private LocalDateTime getEndLocalDateTime(LocalDate end) {
        LocalDateTime endAsLocalDateTime;
        if (end == null) {
            endAsLocalDateTime = LocalDateTime.now();
        } else {
            endAsLocalDateTime = LocalDateTime.of(end, LocalTime.of(23, 59));
        }
        return endAsLocalDateTime;
    }

    @FunctionalInterface
    interface Function7<One, Two, Three, Four, Five, Six, Seven, Eight> {

        Eight apply(One one, Two two, Three three, Four four, Five five, Six six, Seven seven);
    }
}
