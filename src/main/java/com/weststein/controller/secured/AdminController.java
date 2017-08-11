package com.weststein.controller.secured;

import com.weststein.controller.secured.model.RequestedUsersModel;
import com.weststein.controller.secured.model.UserProfileModel;
import com.weststein.email.EmailTextSource;
import com.weststein.handler.application.ConfirmRequestedUserHandler;
import com.weststein.handler.application.DeclineRequestedUserHandler;
import com.weststein.handler.application.GetRequestedUsersHandler;
import com.weststein.handler.application.RequestNewUserHandler;
import com.weststein.handler.user.ConfirmPhoneNumberHandler;
import com.weststein.handler.user.UserInformationHandler;
import com.weststein.handler.user.ValidatePhoneNumberHandler;
import com.weststein.repository.UserInformation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private GetRequestedUsersHandler getRequestedUsersHandler;
    @Autowired
    private DeclineRequestedUserHandler declineRequestedUserHandler;
    @Autowired
    private ConfirmRequestedUserHandler confirmRequestedUserHandler;

    @GetMapping("/api/admin/user/requested")
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public RequestedUsersModel getRequestedUsers() {
        return RequestedUsersModel.builder()
                .requestedUserModels(getRequestedUsersHandler.handle())
                .build();
    }

    @PostMapping("/api/admin/user/requested/decline")
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public void declinePermission(Long id) {
        declineRequestedUserHandler.handle(id);
    }

    @PostMapping("/api/admin/user/requested/confirm")
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public void grantPermission(Long id) {
        confirmRequestedUserHandler.handle(id);
    }



}