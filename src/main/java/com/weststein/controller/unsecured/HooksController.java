package com.weststein.controller.unsecured;

import com.weststein.handler.account.SaveBookingHandler;
import com.weststein.handler.identification.SavePersonIdentificationHandler;
import com.weststein.integration.SolarisBooking;
import com.weststein.integration.SolarisIdentification;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HooksController {

    @Autowired
    private SavePersonIdentificationHandler savePersonIdentificationHandler;
    @Autowired
    private SaveBookingHandler saveBookingHandler;

    @PostMapping("/api/hooks/identification")
    @ApiOperation(value = "web hook for identification call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity identificationHook(@RequestBody(required = false) SolarisIdentification identification) {

        savePersonIdentificationHandler.handle(identification);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/hooks/bookings")
    @ApiOperation(value = "web hook for booking call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity bookingHook(@RequestBody(required = false) SolarisBooking booking) {
        saveBookingHandler.handle(booking);
        return ResponseEntity.ok().build();
    }

}
