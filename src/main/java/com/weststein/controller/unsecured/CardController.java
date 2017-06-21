package com.weststein.controller.unsecured;

import com.weststein.handler.application.GetCardInfoHandler;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    private GetCardInfoHandler getCardInfoHandler;

    @PostMapping("/api/open/card-info")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public AccountAPIv2CardInfo getCardInfo(String cardHolderId){

        return getCardInfoHandler.handle(cardHolderId);

    }
}
