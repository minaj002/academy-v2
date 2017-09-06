package com.weststein.controller.unsecured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.handler.application.RequestResetPasswordHandler;
import com.weststein.handler.application.ResetPasswordHandler;
import com.weststein.handler.application.VerifyTokenHandler;
import com.weststein.handler.viewstatement.StatementCacheHelper;
import com.weststein.infrastructure.MessageBean;
import com.weststein.repository.UserProfile;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.web.bind.annotation.*;

import static java.lang.Thread.sleep;

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
    @Autowired
    private MessageBean messageBean;

    @PostMapping("/api/auth/login")
    @ApiOperation(value = "allow user to login, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<UserProfile> login(@RequestParam String username, @RequestParam String password) {
        return ResponseWrapper.<UserProfile>builder()
                .response(userService.getCurrentUserCredentials().getUserProfile())
                .messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/auth/confirm/{token}")
    @ApiOperation(value = "allow user to verify login information, receives authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper confirm(@RequestParam String username, @RequestParam String password, @PathVariable String token) {
        verifyTokenHandler.handle(username, token);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/reset/{token}")
    @ApiOperation(value = "allow user to reset password using provided token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper reset(@RequestParam String username, @RequestParam String password, @PathVariable String token) {
        resetPasswordHandler.handle(username, password, token);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/reset/request")
    @ApiOperation(value = "allow user to request password reset token(token is sent over the email)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper resetRequest(@RequestParam String username) {
        requestResetPasswordHandler.handle(username);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages()).build();
    }

}
