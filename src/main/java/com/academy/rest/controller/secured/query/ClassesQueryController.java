package com.academy.rest.controller.secured.query;


import com.academy.core.query.GetClassesForDateQuery;
import com.academy.core.query.result.GetClassesForDateResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.ResponseWrapper;
import com.academy.rest.api.ClassAttended;
import com.academy.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/classes")
@Slf4j
public class ClassesQueryController {

    @Autowired
    QueryService queryService;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{date}")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<ClassAttended>> getAllClasses(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) throws ParseException {


        GetClassesForDateQuery query = new GetClassesForDateQuery(userService.getUserName(), date);
        GetClassesForDateResult classes = queryService.execute(query);

        List<ClassAttended> classesJson = objectMapper.map(classes.getClassesAttended(), ClassAttended.class);
        return ResponseWrapper.<List<ClassAttended>>builder().response(classesJson).build();
    }


}
