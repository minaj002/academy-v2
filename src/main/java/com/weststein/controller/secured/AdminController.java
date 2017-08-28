package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.RequestedUserModel;
import com.weststein.controller.secured.model.business.AuthorizedUser;
import com.weststein.handler.administartion.GetEmailsHandler;
import com.weststein.handler.administartion.GetUsersWithoutRolesHandler;
import com.weststein.handler.application.ConfirmRequestedUserHandler;
import com.weststein.handler.application.DeclineRequestedUserHandler;
import com.weststein.handler.application.DeleteUserHandler;
import com.weststein.handler.application.GetRequestedUsersHandler;
import com.weststein.infrastructure.MessageBean;
import com.weststein.repository.SentEmail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private GetUsersWithoutRolesHandler getUsersWithoutRolesHandler;
    @Autowired
    private GetEmailsHandler getEmailsHandler;
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

    @GetMapping("/api/admin/user/without-roles")
    @ApiOperation(value = "get users without roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<AuthorizedUser>> getUsersWithoutRoles() {
        return ResponseWrapper.<List<AuthorizedUser>>builder()
                .response(getUsersWithoutRolesHandler.handle())
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
    @ApiOperation(value = "decline role to requested user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper declinePermission(@PathVariable Long id) {
        declineRequestedUserHandler.handle(id);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @PostMapping("/api/admin/user/{id}/requested/confirm")
    @ApiOperation(value = "confirm role to requested user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper grantPermission(@PathVariable Long id) {
        confirmRequestedUserHandler.handle(id);
        return ResponseWrapper.builder().messages(messageBean.getMessages()).build();
    }

    @GetMapping("/api/admin/emails")
    @ApiOperation(value = "get all emails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper getSentEmails(String email) {
        return ResponseWrapper.<List<SentEmail>>builder().response(getEmailsHandler.handle(email)).messages(messageBean.getMessages()).build();
    }

}