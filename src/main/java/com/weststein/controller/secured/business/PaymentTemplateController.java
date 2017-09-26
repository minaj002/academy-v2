package com.weststein.controller.secured.business;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.TransferTemplateModel;
import com.weststein.handler.template.CreatePaymentTemplateHandler;
import com.weststein.handler.template.DeletePaymentTemplateHandler;
import com.weststein.handler.template.GetPaymentTemplateHandler;
import com.weststein.handler.template.GetPaymentTemplatesHandler;
import com.weststein.infrastructure.MessageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentTemplateController {

    @Autowired
    private CreatePaymentTemplateHandler createPaymentTemplateHandler;
    @Autowired
    private GetPaymentTemplatesHandler getPaymentTemplatesHandler;
    @Autowired
    private GetPaymentTemplateHandler getPaymentTemplateHandler;
    @Autowired
    private DeletePaymentTemplateHandler deletePaymentTemplateHandler;
    @Autowired
    private MessageBean messageBean;

    @PreAuthorize("hasPermission(#businessId, 'OWNER,FINANCIAL_OFFICER')")
    @PostMapping("/api/business/{businessId}/payment/{cardHolderId}/template")
    @ApiOperation(value = "Create payment template")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper create(@PathVariable Long businessId,
                                  TransferTemplateModel transferTemplateModel) {
        return ResponseWrapper.builder()
                .response(createPaymentTemplateHandler.handle(businessId, transferTemplateModel))
                .messages(messageBean.getMessages()).build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER,FINANCIAL_OFFICER')")
    @GetMapping("/api/business/{businessId}/templates")
    @ApiOperation(value = "Get payment templates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper getTemplates(@PathVariable Long businessId,
                                        @RequestParam int size,
                                        @RequestParam int page) {
        return ResponseWrapper.<List<TransferTemplateModel>>builder()
                .response(getPaymentTemplatesHandler.handle(businessId, size, page))
                .messages(messageBean.getMessages()).build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER,FINANCIAL_OFFICER')")
    @GetMapping("/api/business/{businessId}/templates/{templateId}")
    @ApiOperation(value = "Get payment templates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper getTemplate(@PathVariable Long businessId,
                                        @PathVariable Long templateId) {
        return ResponseWrapper.<TransferTemplateModel>builder()
                .response(getPaymentTemplateHandler.handle(templateId))
                .messages(messageBean.getMessages()).build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER,FINANCIAL_OFFICER')")
    @DeleteMapping("/api/business/{businessId}/templates/{templateId}")
    @ApiOperation(value = "Delete payment templates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper deleteTemplate(@PathVariable Long businessId,
                                        @PathVariable Long templateId) {
        deletePaymentTemplateHandler.handle(templateId);
        return ResponseWrapper.<TransferTemplateModel>builder()
                .messages(messageBean.getMessages()).build();
    }
}