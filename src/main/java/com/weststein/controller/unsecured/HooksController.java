package com.weststein.controller.unsecured;

import com.weststein.handler.account.SaveBookingHandler;
import com.weststein.handler.identification.SavePersonIdentificationHandler;
import com.weststein.integration.SolarisBooking;
import com.weststein.integration.SolarisIdentification;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
//    public ResponseEntity identificationHook() {
    public ResponseEntity identificationHook(@RequestBody(required = false) SolarisIdentification identification) {
        log.info("Identification hook with content: ");
        savePersonIdentificationHandler.handle(identification);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/hooks/bookings")
    @ApiOperation(value = "web hook for booking call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity bookingHook(@RequestBody(required = false) SolarisBooking booking) {
        log.info("Booking hook with content: " + booking);
        saveBookingHandler.handle(booking);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/hooks/person")
    @ApiOperation(value = "allow user to login, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity personHook(@RequestParam(value = "person_id", required = false) String solarisId, @RequestParam(value = "status", required = false) String status) {
        log.info("Booking hook with content: ");

        return ResponseEntity.ok().build();
    }


}
