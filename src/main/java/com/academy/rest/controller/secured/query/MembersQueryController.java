package com.academy.rest.controller.secured.query;

import com.academy.rest.ResponseWrapper;
import com.academy.rest.api.Member;
import com.academy.core.query.GetAcademyMembersQuery;
import com.academy.core.query.result.GetAcademyMembersResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.security.model.entity.UserContext;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/api/members")
@Slf4j
public class MembersQueryController {

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
    public ResponseWrapper<Collection<Member>> getAllMembers() {

        String name = getUserName();

        log.debug("Getting members for ", name);
        GetAcademyMembersQuery query = new GetAcademyMembersQuery(name);
        GetAcademyMembersResult members = queryService.execute(query);

        List<Member> membersJson = objectMapper.map(members.getMembers(), Member.class);

        return ResponseWrapper.<Collection<Member>>builder().response(membersJson).build();
    }

    private String getUserName() {
        return ((UserContext)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
