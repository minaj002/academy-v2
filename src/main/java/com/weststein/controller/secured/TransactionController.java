package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.handler.transaction.*;
import com.weststein.handler.viewstatement.TransactionType;
import com.weststein.infrastructure.MessageBean;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepositVoucherHandler depositVoucherHandler;
    @Autowired
    private SepaPaymentHandler sepaPaymentHandler;
    @Autowired
    private CreateSepaPaymentHandler createSepaPaymentHandler;
    @Autowired
    private CardToCardPaymentHandler cardToCardPaymentHandler;
    @Autowired
    private GetSepaPaymentHandler getSepaPaymentHandler;
    @Autowired
    private GetSepaPaymentsHandler getSepaPaymentsHandler;
    @Autowired
    private ConfirmSepaPaymentHandler confirmSepaPaymentHandler;
    @Autowired
    private DeleteSepaPaymentHandler deleteSepaPaymentHandler;
    @Autowired
    private RejectSepaPaymentHandler rejectSepaPaymentHandler;
    @Autowired
    private GetSepaPaymentAsPDFHandler getSepaPaymentAsPDFHandler;
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

    @PostMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa/create")
    @ApiOperation(value = "Create SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> createBusinessSepaPayment(@PathVariable Long businessId, @PathVariable Long cardHolderId, @RequestBody SepaTransferModel sepa) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        return ResponseWrapper.<Long>builder()
                .response(createSepaPaymentHandler.handle(cardHolderId, sepa))
                .messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa/confirm/{paymentId}")
    @ApiOperation(value = "Confirm SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper confirmBusinessSepaPayment(@PathVariable Long businessId, @PathVariable Long cardHolderId, @PathVariable Long paymentId, BigDecimal amount) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        return ResponseWrapper.builder().response(confirmSepaPaymentHandler.handle(paymentId, amount)).messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa/sign/{paymentId}")
    @ApiOperation(value = "Send to sign SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper signBusinessSepaPayment(@PathVariable Long businessId, @PathVariable Long cardHolderId, @PathVariable Long paymentId) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        return ResponseWrapper.builder().response(sepaPaymentHandler.handle(paymentId)).messages(messageBean.getMessages()).build();
    }

    @GetMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa/{paymentId}")
    @ApiOperation(value = "Send to sign SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<SepaTransferModel> getBusinessSepaPayment(@PathVariable Long businessId,
                                                                     @PathVariable Long cardHolderId, @PathVariable Long paymentId) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        return ResponseWrapper.<SepaTransferModel>builder()
                .response(getSepaPaymentHandler.handle(paymentId))
                .messages(messageBean.getMessages()).build();
    }

    @GetMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa")
    @ApiOperation(value = "Send to sign SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<SepaTransferModel>> getBusinessSepaPayments(@PathVariable Long businessId,
                                                                            @PathVariable Long cardHolderId,
                                                                            @RequestParam int size,
                                                                            @RequestParam int page) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        return ResponseWrapper.<List<SepaTransferModel>>builder()
                .response(getSepaPaymentsHandler.handle(cardHolderId, size, page))
                .messages(messageBean.getMessages()).build();
    }

    @DeleteMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa/{paymentId}")
    @ApiOperation(value = "Send to sign SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper deleteBusinessSepaPayment(@PathVariable Long businessId,
                                                     @PathVariable Long cardHolderId, @PathVariable Long paymentId) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        deleteSepaPaymentHandler.handle(paymentId);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages()).build();
    }

    @PutMapping("/api/business/{businessId}/payment/{cardHolderId}/sepa/reject/{paymentId}")
    @ApiOperation(value = "Send to sign SEPA payment for business cardholder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper rejectBusinessSepaPayment(@PathVariable Long businessId,
                                                     @PathVariable Long cardHolderId, @PathVariable Long paymentId) {
        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        rejectSepaPaymentHandler.handle(paymentId);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages()).build();
    }

    @GetMapping(value = "/api/business/{businessId}/payment/{cardHolderId}/sepa/pdf/{paymentId}", produces = "application/pdf")
    @ApiOperation(value = "allows user to download business sepa payment as pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Resource viewBusinessSepaPaymentPdf(@PathVariable Long businessId, @PathVariable Long cardHolderId, @PathVariable Long paymentId) {
//        userService.isAuthorizedForBusinessCardHolder(businessId, cardHolderId);
        ByteArrayOutputStream res = getSepaPaymentAsPDFHandler.handle(businessId, paymentId);
        return new ByteArrayResource(res.toByteArray());
    }
}
