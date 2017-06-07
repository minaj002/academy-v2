package com.weststein.controller.unsecured;

import com.weststein.configuration.HookSignatures;
import com.weststein.handler.account.SaveBookingHandler;
import com.weststein.handler.identification.SavePersonIdentificationHandler;
import com.weststein.integration.SolarisBooking;
import com.weststein.integration.SolarisIdentification;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

@Slf4j
@RestController
public class HooksController {

    @Autowired
    private SavePersonIdentificationHandler savePersonIdentificationHandler;
    @Autowired
    private SaveBookingHandler saveBookingHandler;
    @Autowired
    private HookSignatures signatures;

    @PostMapping("/api/hooks/identification")
    @ApiOperation(value = "web hook for identification call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity identificationHook(@RequestHeader(value="Solaris-Webhook-Signature") String signatureWithAlgorithm,
                                             @RequestHeader(value="Solaris-Webhook-Event-Type") String event,
                                             @RequestBody SolarisIdentification identification) {

        String webHookSignature[] = signatureWithAlgorithm.split("=");

        log.info("algorithm:" + webHookSignature[0] + ", sign:" + webHookSignature[1]);

        String signature = signatures.getSignature(event);

//                    try {
//                String hmac = calculateRFC2104HMAC(IOUtils.toByteArray(identification.toString()), signature, webHookSignature[0]);
//
//                log.info("Calculated " + hmac);
//                log.info("Received   " + webHookSignature[1]);
//                log.info("are equal " + hmac.equals(webHookSignature[1]));
////                 TODO: Enable latter
//
////                if(!hmac.equals(webHookSignature[1])){
////                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
////                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
////                    return null;
////                }
//            } catch (SignatureException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (InvalidKeyException e) {
//                e.printStackTrace();
//            }



        log.info("Identification hook with content: " + identification);
        savePersonIdentificationHandler.handle(identification);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/hooks/bookings")
    @ApiOperation(value = "web hook for booking call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity bookingHook(@RequestBody SolarisBooking booking) {
        log.info("Booking hook with content: " + booking);
        saveBookingHandler.handle(booking);
        return ResponseEntity.ok().build();
    }

    private static String calculateRFC2104HMAC(byte[] data, String key, String algorithm)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "Hmac" + algorithm);
        Mac mac = Mac.getInstance("Hmac" + algorithm);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data));
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

}
