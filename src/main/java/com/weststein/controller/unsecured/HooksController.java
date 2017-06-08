package com.weststein.controller.unsecured;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestController
public class HooksController {

    @Autowired
    private SavePersonIdentificationHandler savePersonIdentificationHandler;
    @Autowired
    private SaveBookingHandler saveBookingHandler;
    @Autowired
    private HookSignatures signatures;
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/api/hooks/identification")
    @ApiOperation(value = "web hook for identification call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity identificationHook(@RequestHeader(value="Solaris-Webhook-Signature") String signatureWithAlgorithm,
                                             @RequestHeader(value="Solaris-Webhook-Event-Type") String event,
                                             HttpServletRequest request) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(request.getInputStream(), baos);

            if (isAuthorized(signatureWithAlgorithm, event, baos)) {
                return ResponseEntity.status(UNAUTHORIZED).build();
            }
            SolarisIdentification identification = mapper.readerFor(SolarisIdentification.class).readValue(baos.toByteArray());
            log.info("Identification hook with content: " + identification);
            savePersonIdentificationHandler.handle(identification);
        } catch (SignatureException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (InvalidKeyException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/hooks/bookings")
    @ApiOperation(value = "web hook for booking call from solaris")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity bookingHook(@RequestHeader(value="Solaris-Webhook-Signature") String signatureWithAlgorithm,
                                      @RequestHeader(value="Solaris-Webhook-Event-Type") String event,
                                      HttpServletRequest request) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(request.getInputStream(), baos);

            if (isAuthorized(signatureWithAlgorithm, event, baos)) {
                return ResponseEntity.status(UNAUTHORIZED).build();
            }
            SolarisBooking booking = mapper.readerFor(SolarisBooking.class).readValue(baos.toByteArray());
            log.info("Booking hook with content: " + booking);
            saveBookingHandler.handle(booking);
        } catch (SignatureException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (InvalidKeyException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().build();
    }

    static String calculateRFC2104HMAC(byte[] data, String key, String algorithm)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "Hmac" + algorithm);
        Mac mac = Mac.getInstance("Hmac" + algorithm);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data));
    }

    private boolean isAuthorized(@RequestHeader(value = "Solaris-Webhook-Signature") String signatureWithAlgorithm, @RequestHeader(value = "Solaris-Webhook-Event-Type") String event, ByteArrayOutputStream baos) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        String webHookSignature[] = signatureWithAlgorithm.split("=");
        String signature = signatures.getSignature(event);

        String hmac = calculateRFC2104HMAC(baos.toByteArray(), signature, webHookSignature[0]);
        log.info("Calculated " + hmac);
        log.info("Received   " + webHookSignature[1]);
        log.info("are equal " + hmac.equals(webHookSignature[1]));
        if(!hmac.equals(webHookSignature[1])){
            return false;
        }
        return true;
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

}
