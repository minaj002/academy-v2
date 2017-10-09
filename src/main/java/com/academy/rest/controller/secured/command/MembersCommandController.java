package com.academy.rest.controller.secured.command;


import com.academy.rest.api.Member;
import com.academy.core.command.AddMemberCommand;
import com.academy.core.command.DeleteMemberCommand;
import com.academy.core.command.EditMemberCommand;
import com.academy.core.command.result.AddMemberResult;
import com.academy.core.command.result.DeleteMemberResult;
import com.academy.core.command.result.EditMemberResult;
import com.academy.core.command.service.CommandService;
import com.academy.core.dto.MemberBean;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MembersCommandController {

    private static Logger LOG = LoggerFactory
            .getLogger(MembersCommandController.class);

    @Autowired
    CommandService commandService;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<String> addNewMember(@RequestBody Member member) {

        AddMemberCommand command = new AddMemberCommand(objectMapper.map(member, MemberBean.class));
        command.setUserName(userService.getUserName());
        AddMemberResult result = commandService.execute(command);
        if (!StringUtils.isEmpty(result.getMemberId())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<String> editMember(@RequestBody Member member) {

        EditMemberCommand command = new EditMemberCommand(objectMapper.map(member, MemberBean.class));
        command.setUserName(userService.getUserName());

        EditMemberResult result = commandService.execute(command);

        if (!StringUtils.isEmpty(result.getMemberId())) {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<String> deleteMember(@RequestBody Member member) {

        DeleteMemberCommand command = new DeleteMemberCommand(objectMapper.map(member, MemberBean.class));

        DeleteMemberResult result = commandService.execute(command);

        if (!StringUtils.isEmpty(result.getMemberId())) {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handle(Exception exception) {
        LOG.error("Exception while processing incoming request.", exception);
        return "Unexpected error!";
    }

}
