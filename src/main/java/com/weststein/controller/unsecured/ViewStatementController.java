package com.weststein.controller.unsecured;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.ViewStatement;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import com.weststein.integration.response.ViewStatementResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewStatementController {

    @Autowired
    private PPFService<ViewStatement, AccountAPIv2ViewStatement> ppfService;

    @GetMapping("/api/view-statement")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public ResponseEntity<ViewStatementResponse> view(){

        ViewStatement object = new ViewStatement();
        object.setCardholderid("400000557017");
        object.setEndDate("2016-12-03");
        object.setStartDate("2016-01-01");
        object.setViewStyle("Y");
        AccountAPIv2ViewStatement res = ppfService.get(object, AccountAPIv2ViewStatement.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(res.getViewStatement());
    }

}
