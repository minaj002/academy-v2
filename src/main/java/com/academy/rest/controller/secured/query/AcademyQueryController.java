package com.academy.rest.controller.secured.query;

import com.academy.rest.ResponseWrapper;
import com.academy.rest.api.Academy;
import com.academy.core.query.GetAcademiesQuery;
import com.academy.core.query.GetAcademyQuery;
import com.academy.core.query.result.GetAcademiesResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/academies")
public class AcademyQueryController {

    private static Logger LOG = LoggerFactory
            .getLogger(AcademyQueryController.class);

    @Autowired
    QueryService queryService;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<Academy>> getAllAcademies() {

        GetAcademiesQuery query = new GetAcademiesQuery();
        GetAcademiesResult academies = queryService.execute(query);
        List<Academy> academyJson = objectMapper.map(academies.getAcademies(), Academy.class);

        return ResponseWrapper.<List<Academy>>builder().response(academyJson).build();
    }


    @RequestMapping(method = RequestMethod.GET, path = "/me")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Academy getAcademy() {


        GetAcademyQuery query = new GetAcademyQuery();
        query.setName(userService.getUserName());
        GetAcademiesResult academies = queryService.execute(query);
        List<Academy> academyJson = objectMapper.map(academies.getAcademies(), Academy.class);

        return academyJson.stream().findFirst().get();
    }

}
