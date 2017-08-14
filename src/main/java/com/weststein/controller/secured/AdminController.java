package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.RequestedUserModel;
import com.weststein.handler.application.ConfirmRequestedUserHandler;
import com.weststein.handler.application.DeclineRequestedUserHandler;
import com.weststein.handler.application.DeleteUserHandler;
import com.weststein.handler.application.GetRequestedUsersHandler;
import com.weststein.infrastructure.MessageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private GetRequestedUsersHandler getRequestedUsersHandler;
    @Autowired
    private DeclineRequestedUserHandler declineRequestedUserHandler;
    @Autowired
    private ConfirmRequestedUserHandler confirmRequestedUserHandler;
    @Autowired
    private DeleteUserHandler deleteUserHandler;
    @Autowired
    private MessageBean messageBean;

    @GetMapping("/api/admin/user/requested")
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<RequestedUserModel>> getRequestedUsers() {
        return ResponseWrapper.<List<RequestedUserModel>>builder()
                .response(getRequestedUsersHandler.handle())
                .messages(messageBean.getMessages())
                .build();

    }

    @DeleteMapping("/api/admin/user/{id}")
    @ApiOperation(value = "delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper deleteUser(@PathVariable Long id) {
        deleteUserHandler.handle(id);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/admin/user/{id}/requested/decline")
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper declinePermission(@PathVariable Long id) {
        declineRequestedUserHandler.handle(id);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/admin/user/{id}/requested/confirm")
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper grantPermission(@PathVariable Long id) {
        confirmRequestedUserHandler.handle(id);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

}