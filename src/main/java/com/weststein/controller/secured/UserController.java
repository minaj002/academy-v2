package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.UserProfileModel;
import com.weststein.email.EmailTextSource;
import com.weststein.handler.application.RequestNewUserHandler;
import com.weststein.handler.user.ChangePasswordHandler;
import com.weststein.handler.user.ConfirmPhoneNumberHandler;
import com.weststein.handler.user.UserInformationHandler;
import com.weststein.handler.user.ValidatePhoneNumberHandler;
import com.weststein.infrastructure.MessageBean;
import com.weststein.repository.UserInformation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserInformationHandler userInformationHandler;
    @Autowired
    private ValidatePhoneNumberHandler validatePhoneNumberHandler;
    @Autowired
    private ConfirmPhoneNumberHandler confirmPhoneNumberHandler;
    @Autowired
    private RequestNewUserHandler requestNewUserHandler;
    @Autowired
    private ChangePasswordHandler changePasswordHandler;
    @Autowired
    private MessageBean messageBean;

    @GetMapping("/api/user")
    @ApiOperation(value = "User Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<UserInformation> getUserInfo() {
        return ResponseWrapper.<UserInformation>builder().response(userInformationHandler.handle()).messages(messageBean.getMessages()).build();
    }

    @GetMapping("/api/user/phone/validate")
    @ApiOperation(value = "Validate phone number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper validatePhoneNumber() {
        validatePhoneNumberHandler.handle();
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/user/phone/confirm-validation")
    @ApiOperation(value = "Confirm phone number validation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper confirmPhoneNumber(String code) {
        confirmPhoneNumberHandler.handle(code);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/user/request-new")
    @ApiOperation(value = "request new user for business account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper requestNewUser(UserProfileModel userProfileModel) {
        requestNewUserHandler.handle(userProfileModel);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/user/change-password")
    @ApiOperation(value = "change password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper changePassword(String oldPassword, String newPassword) {
        changePasswordHandler.handle(oldPassword, newPassword);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }


}