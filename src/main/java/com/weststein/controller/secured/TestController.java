package com.weststein.controller.secured;


import com.weststein.controller.secured.model.FullModel;
import com.weststein.handler.SynchronizationHandler;
import com.weststein.repository.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private SynchronizationHandler synchronizationHandler;

    @PostMapping
    @ApiOperation(value = "synchronize local db with solaris db", response = FullModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public FullModel synchronize() {
        return synchronizationHandler.handle();
    }

}
