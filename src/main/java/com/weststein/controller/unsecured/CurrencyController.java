package com.weststein.controller.unsecured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.handler.currency.GetAvailableCurrenciesHandler;
import com.weststein.repository.Currency;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by artis on 15/09/2017.
 */

@RestController
@RequestMapping("api/open/currency")
public class CurrencyController {

    @Autowired
    private GetAvailableCurrenciesHandler availableCurrenciesHandler;

    @GetMapping
    @ApiOperation(value = "Available currencies list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<Currency>> getAvailableCurrencies() {
        return ResponseWrapper.<List<Currency>>builder().response(availableCurrenciesHandler.handle()).build();
    }

}
