package com.weststein.controller.unsecured;

import com.weststein.handler.application.RequestResetPasswordHandler;
import com.weststein.handler.application.ResetPasswordHandler;
import com.weststein.handler.application.VerifyTokenHandler;
import com.weststein.repository.UserProfile;
import com.weststein.security.UserService;
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
    @Autowired
    private ResetPasswordHandler resetPasswordHandler;
    @Autowired
    private UserService userService;

    @PostMapping("/api/auth/login")
    @ApiOperation(value = "allow user to login, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public UserProfile login(@RequestParam String username, @RequestParam String password) {
        return userService.getCurrentUserCredentials().getUserProfile();
    }

    @PostMapping("/api/auth/confirm/{token}")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void confirm(@RequestParam String username, @RequestParam String password, @PathVariable String token) {
        verifyTokenHandler.handle(username, token);
    }

    @PostMapping("/api/reset/{token}")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void reset(@RequestParam String username, @RequestParam String password, @PathVariable String token) {
        resetPasswordHandler.handle(username, password, token);
    }

    @PostMapping("/api/reset/request")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void resetRequest(@RequestParam String username) {
        requestResetPasswordHandler.handle(username);
    }



}
