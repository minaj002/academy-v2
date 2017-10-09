package com.academy.rest.controller.secured.command;


import com.academy.core.command.AddClassCommand;
import com.academy.core.command.DeleteClassCommand;
import com.academy.core.command.result.AddClassResult;
import com.academy.core.command.result.DeleteClassResult;
import com.academy.core.command.service.CommandService;
import com.academy.core.dto.ClassAttendedBean;
import com.academy.core.dto.MemberBean;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.api.ClassAttended;
import com.academy.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesCommandController {

    private static Logger LOG = LoggerFactory.getLogger(ClassesCommandController.class);

    @Autowired
    CommandService commandService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    @ResponseBody
    public ResponseEntity<String> addClass(@RequestBody ClassAttended classAttended) {

        List<MemberBean> memberBeans = objectMapper.map(classAttended.getMembers(), MemberBean.class);
        AddClassCommand command = new AddClassCommand(new Date(), memberBeans);
        command.setUserName(userService.getUserName());
        AddClassResult result = commandService.execute(command);

        if (!StringUtils.isEmpty(result.getId())) {
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteClass(@RequestBody ClassAttended classAttended) {

        DeleteClassCommand command = new DeleteClassCommand(objectMapper.map(classAttended, ClassAttendedBean.class));
        DeleteClassResult result = commandService.execute(command);
        if (!StringUtils.isEmpty(result.getClassId())) {
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
