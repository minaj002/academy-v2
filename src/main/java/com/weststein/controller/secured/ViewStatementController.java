package com.weststein.controller.secured;

import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.handler.viewstatement.ViewStatementHandler;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ViewStatementController {

    @Autowired
    private UserService userService;
    @Autowired
    private ViewStatementHandler viewStatementHandler;

    @GetMapping("/api/view-statement/{cardHolderId}")
    @ApiOperation(value = "allows user to view his statement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ViewStatementModel view(@PathVariable String cardHolderId, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate start, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam LocalDate end) {
        userService.isAuthorizedForCardHolder(cardHolderId);
        return viewStatementHandler.handle(cardHolderId, start, end);
    }

}