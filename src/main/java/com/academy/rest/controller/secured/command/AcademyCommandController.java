package com.academy.rest.controller.secured.command;


import com.academy.core.command.AddAcademyCommand;
import com.academy.core.command.AddSectionToAcademyCommand;
import com.academy.core.command.result.AddAcademyResult;
import com.academy.core.command.result.AddSectionResult;
import com.academy.core.command.service.CommandService;
import com.academy.core.dto.SectionBean;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.api.Academy;
import com.academy.rest.api.Section;
import com.academy.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/academy")
@Slf4j
public class AcademyCommandController {

    @Autowired
    CommandService commandService;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/new")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<String> addNewMember(@RequestBody Academy academy) {

        AddAcademyResult result = commandService.execute(objectMapper.map(academy, AddAcademyCommand.class));

        if (!StringUtils.isEmpty(result.getId())) {
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "/section")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<String> addSection(@RequestBody Section section) {

        AddSectionResult result = commandService.execute(new AddSectionToAcademyCommand(objectMapper.map(section, SectionBean.class), userService.getUserName()));

        if (!StringUtils.isEmpty(result.getId())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handle(Exception exception) {
        log.error("Exception while processing incoming request.", exception);
        return "Unexpected error!";
    }

}
