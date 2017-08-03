package com.weststein.controller.unsecured;

import com.weststein.handler.application.RequestResetPasswordHandler;
import com.weststein.handler.application.VerifyTokenHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private VerifyTokenHandler verifyTokenHandler;
    @Autowired
    private RequestResetPasswordHandler requestResetPasswordHandler;

    @PostMapping("/api/auth/login")
    @ApiOperation(value = "allow user to login, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void login(@RequestParam String username, @RequestParam String password) {
    }

    @PostMapping("/api/auth/confirm/{token}")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void confirm(@RequestParam String username, @RequestParam String password, @PathVariable String token) {
        verifyTokenHandler.handle(username, token);
    }

    @PostMapping("/api/auth/reset/{token}")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void reset(@RequestParam String username, @RequestParam String password, @PathVariable String token) {
        verifyTokenHandler.handle(username, token);
    }

    @PostMapping("/api/auth/reset/request")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void resetRequest(@RequestParam String username) {
        requestResetPasswordHandler.handle(username);
    }

}
