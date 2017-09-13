package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.handler.transaction.CardToCardPaymentHandler;
import com.weststein.handler.transaction.DepositVoucherHandler;
import com.weststein.infrastructure.MessageBean;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class TransactionController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepositVoucherHandler depositVoucherHandler;
    @Autowired
    private CardToCardPaymentHandler cardToCardPaymentHandler;
    @Autowired
    private MessageBean messageBean;

    @PostMapping("/api/payment/deposit-voucher/{cardHolderId}")
    @ApiOperation(value = "depositVoucher")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper depositVoucher(@PathVariable Long cardHolderId, String voucher) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        depositVoucherHandler.handle(cardHolderId, voucher);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/payment/sepa/{cardHolderId}")
    @ApiOperation(value = "sepaPayment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper sepaPayment(@PathVariable Long cardHolderId, @RequestBody SepaTransferModel sepa) {
        userService.isAuthorizedForCardHolder(cardHolderId);
//        sepaPaymentHandler.handle(cardHolderId, sepa);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/payment/card-to-card/{cardHolderId}")
    @ApiOperation(value = "cardToCardPayment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper cardToCardPayment(@PathVariable Long cardHolderId, BigDecimal amount, String cardNumberTo) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        cardToCardPaymentHandler.handle(cardHolderId, cardNumberTo, amount);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

}
