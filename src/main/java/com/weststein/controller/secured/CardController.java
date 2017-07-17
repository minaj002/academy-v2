package com.weststein.controller.secured;

import com.weststein.controller.secured.model.CardHolderModel;
import com.weststein.controller.secured.model.CardInfoModel;
import com.weststein.controller.secured.model.CardholderIdsModel;
import com.weststein.handler.card.*;
import com.weststein.repository.UserInformation;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    private GetCardHolderIdsHandler getCardHolderIdsHandler;
    @Autowired
    private CardRequestHandler cardRequestHandler;

    @GetMapping("/api/card-request")
    @ApiOperation(value = "Request Card")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public UserInformation requestCard() {
        return cardRequestHandler.handle();
    }

    @GetMapping("/api/card-holder")
    @ApiOperation(value = "getCardHolderIds")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public CardholderIdsModel getCardholderIds() {
        return CardholderIdsModel.builder().cardholderIds(getCardHolderIdsHandler.handle()).build();
    }

    @GetMapping("/api/card-info/{cardHolderId}")
    @ApiOperation(value = "getCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public CardInfoModel getCardInfo(@PathVariable String cardHolderId) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        return getCardInfoHandler.handle(cardHolderId);
    }

    @PutMapping("/api/card-info/{cardHolderId}")
    @ApiOperation(value = "updateCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCardInfo(@PathVariable String cardHolderId, @RequestBody CardHolderModel cardHolder) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        updateCardHolderHandler.handle(cardHolderId, cardHolder);

    }

    @PostMapping("/api/card-info/pin-reminder/{cardHolderId}")
    @ApiOperation(value = "pinRemiobder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void pinReminder(@PathVariable String cardHolderId) {
        pinReminderHandler.handle(cardHolderId);
    }
}
