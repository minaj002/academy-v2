package com.academy.rest.controller.secured.query;

import com.academy.core.query.GetAcademyMembersQuery;
import com.academy.core.query.GetClassesForMemberQuery;
import com.academy.core.query.result.GetAcademyMembersResult;
import com.academy.core.query.result.GetClassesForMemberResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.ResponseWrapper;
import com.academy.rest.api.ClassAttended;
import com.academy.rest.api.Member;
import com.academy.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/members")
@Slf4j
public class MembersQueryController {

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
    public ResponseWrapper<List<Member>> getAllMembers() {

        GetAcademyMembersQuery query = new GetAcademyMembersQuery(userService.getUserName());
        GetAcademyMembersResult members = queryService.execute(query);

        List<Member> membersJson = objectMapper.map(members.getMembers(), Member.class);
        return ResponseWrapper.<List<Member>>builder().response(membersJson).build();
    }

    @GetMapping(path = "/{id}/classes")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<ClassAttended>> getClassesForMember(@PathVariable Long id) {

        GetClassesForMemberQuery query = GetClassesForMemberQuery.builder()
                .user(userService.getUserName()).memberId(id).build();
        GetClassesForMemberResult members = queryService.execute(query);

        return ResponseWrapper.<List<ClassAttended>>builder().response(objectMapper.map(members.getClassesAttended(), ClassAttended.class)).build();
    }

}
