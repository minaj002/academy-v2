package com.weststein.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    @PostMapping
    @ApiOperation(value = "allow user to login, receives authorization and refresh token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void login(@RequestParam String username, @RequestParam String password){
        // Only used for swagger real work is done in LoginAuthenticationSuccessHandler
    }

}
