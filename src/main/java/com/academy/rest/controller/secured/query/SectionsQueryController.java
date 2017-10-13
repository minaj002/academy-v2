package com.academy.rest.controller.secured.query;

import com.academy.core.query.GetSectionsQuery;
import com.academy.core.query.result.GetSectionsResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.ResponseWrapper;
import com.academy.rest.api.Section;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/sections")
@Slf4j
public class SectionsQueryController {

    @Autowired
    QueryService queryService;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<Section>> getSections() {

        GetSectionsResult sections = queryService.execute(new GetSectionsQuery());
        return ResponseWrapper.<List<Section>>builder()
                .response(objectMapper.map(sections.getSections(), Section.class))
                .build();
    }

}
