package com.weststein.controller.unsecured;

import com.weststein.security.model.UserContext;
import com.weststein.security.model.token.JwtToken;
import com.weststein.security.model.token.JwtTokenFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@RestController

public class LoginController {

    @Autowired
    private JwtTokenFactory tokenFactory;

    @PostMapping("/api/auth/login")
    @ApiOperation(value = "allow user to login, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Map<String, String> login(@RequestParam String username, @RequestParam String password){
        // Only used for swagger real work is done in LoginAuthenticationSuccessHandler

        JwtToken accessToken = tokenFactory.createAccessJwtToken((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", accessToken.getToken());

        return tokenMap;

    }

//    @RequestMapping(value = "/api/auth/login", method = RequestMethod.OPTIONS)
////    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
//    public ResponseEntity handle(@RequestParam String username, @RequestParam String password) {
//        return new ResponseEntity(HttpStatus.OK);
//    }

}