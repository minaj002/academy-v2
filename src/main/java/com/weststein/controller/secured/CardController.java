package com.weststein.controller.secured;

import com.weststein.controller.secured.model.CardHolderModel;
import com.weststein.controller.secured.model.CardInfoModel;
import com.weststein.handler.card.GetCardHolderHandler;
import com.weststein.handler.card.GetCardInfoHandler;
import com.weststein.handler.card.UpdateCardHolderHandler;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {

    @Autowired
    private GetCardInfoHandler getCardInfoHandler;
    @Autowired
    private GetCardHolderHandler getCardHolderHandler;
    @Autowired
    private UpdateCardHolderHandler updateCardHolderHandler;

    @GetMapping("/api/card-info/{cardHolderId}")
    @ApiOperation(value = "getCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public CardInfoModel getCardInfo(@PathVariable String cardHolderId){
        return getCardInfoHandler.handle(cardHolderId);
    }

    @ApiOperation(value = "getCardHolder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public CardHolderModel getCardHolder(@PathVariable String cardHolderId){
        return getCardHolderHandler.handle(cardHolderId);
    }

    @PutMapping("/api/card-info/{cardHolderId}")
    @ApiOperation(value = "updateCardInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCardInfo(CardHolderModel cardHolderId){
        updateCardHolderHandler.handle(cardHolderId);

    }
}
