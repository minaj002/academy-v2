package com.academy.rest.controller.unsecured;

import com.academy.rest.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/api/auth/login")
    @ApiOperation(value = "allow user to login, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<String> login(@RequestParam String username, @RequestParam String password) {
        return ResponseWrapper.<String>builder().response(username).build();
    }


}
