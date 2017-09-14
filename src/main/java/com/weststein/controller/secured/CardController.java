package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.CardHolderModel;
import com.weststein.controller.secured.model.CardInfoModel;
import com.weststein.handler.card.*;
import com.weststein.infrastructure.MessageBean;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserRole;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private UserService userService;
    @Autowired
    private GetCardInfoHandler getCardInfoHandler;
    @Autowired
    private UpdateCardHolderHandler updateCardHolderHandler;
    @Autowired
    private PinReminderHandler pinReminderHandler;
    @Autowired
    private PrivateCardRequestHandler cardRequestHandler;
    @Autowired
    private BlockCardHandler blockCardHandler;
    @Autowired
    private MessageBean messageBean;

    @GetMapping("/api/card-request")
    @ApiOperation(value = "Request Card")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public ResponseWrapper<UserInformation> requestCard() {
        return ResponseWrapper.<UserInformation>builder()
                .response(cardRequestHandler.handle())
                .messages(messageBean.getMessages())
                .build();
    }

    @GetMapping("/api/card-info/{cardHolderId}")
    @ApiOperation(value = "getCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public ResponseWrapper<CardInfoModel> getCardInfo(@PathVariable Long cardHolderId) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        return ResponseWrapper.<CardInfoModel>builder()
                .response(getCardInfoHandler.handle(cardHolderId))
                .messages(messageBean.getMessages())
                .build();
    }

    @PutMapping("/api/card-info/{cardHolderId}")
    @ApiOperation(value = "updateCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper updateCardInfo(@PathVariable Long cardHolderId, @RequestBody CardHolderModel cardHolder) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        updateCardHolderHandler.handle(cardHolderId, cardHolder);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/card-info/pin-reminder/{cardHolderId}")
    @ApiOperation(value = "pinReminder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper pinReminder(@PathVariable Long cardHolderId) {
        userService.isAuthorizedForCardHolder(cardHolderId);
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
