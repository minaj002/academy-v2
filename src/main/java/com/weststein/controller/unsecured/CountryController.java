package com.weststein.controller.unsecured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.handler.country.GetAvailableCountriesHandler;
import com.weststein.repository.Country;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by artis on 14/09/2017.
 */

@RestController
@RequestMapping("api/open/country")
public class CountryController {

    @Autowired
    private GetAvailableCountriesHandler getAvailableCountriesHandler;

    @GetMapping
    @ApiOperation(value = "Avaiable countries list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<Country>> getCountries() {
        return ResponseWrapper.<List<Country>>builder().response(getAvailableCountriesHandler.handle()).build();
    }

}
