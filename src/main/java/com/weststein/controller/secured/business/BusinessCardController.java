package com.weststein.controller.secured.business;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.CardHolderModel;
import com.weststein.controller.secured.model.CardInfoModel;
import com.weststein.handler.card.BlockCardHandler;
import com.weststein.handler.card.GetCardInfoHandler;
import com.weststein.handler.card.PinReminderHandler;
import com.weststein.handler.card.UpdateCardHolderHandler;
import com.weststein.infrastructure.MessageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusinessCardController {

    @Autowired
    private GetCardInfoHandler getCardInfoHandler;
    @Autowired
    private UpdateCardHolderHandler updateCardHolderHandler;
    @Autowired
    private PinReminderHandler pinReminderHandler;
    @Autowired
    private BlockCardHandler blockCardHandler;
    @Autowired
    private MessageBean messageBean;

    @PreAuthorize("hasPermission(#businessId, #cardHolderId,'OWNER,FINANCIAL_OFFICER')")
    @GetMapping("/api/business/{businessId}/card-info/{cardHolderId}")
    @ApiOperation(value = "getCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public ResponseWrapper<CardInfoModel> getBusinessCardInfo(@PathVariable Long businessId, @PathVariable Long cardHolderId) {
        return ResponseWrapper.<CardInfoModel>builder()
                .response(getCardInfoHandler.handle(cardHolderId))
                .messages(messageBean.getMessages())
                .build();
    }

    @PreAuthorize("hasPermission(#businessId, #cardHolderId,'OWNER,FINANCIAL_OFFICER')")
    @PutMapping("/api/business/{businessId}/card-info/{cardHolderId}")
    @ApiOperation(value = "updateCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper updateCardInfo(@PathVariable Long businessId, @PathVariable Long cardHolderId, @RequestBody CardHolderModel cardHolder) {
        updateCardHolderHandler.handle(cardHolderId, cardHolder);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PreAuthorize("hasPermission(#businessId, #cardHolderId,'OWNER,FINANCIAL_OFFICER')")
    @PostMapping("/api/business/{businessId}/card-info/{cardHolderId}/pin-reminder")
    @ApiOperation(value = "pin Reminder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper pinReminder(@PathVariable Long businessId, @PathVariable Long cardHolderId) {
        pinReminderHandler.handle(cardHolderId);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER')")
    @PostMapping("/api/business/{businessId}/card-info/block/{cardholderId}")
    @ApiOperation(value = "pinReminder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper blockCard(@PathVariable Long businessId, @PathVariable Long cardholderId) {
        blockCardHandler.handle(cardholderId);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }
}
