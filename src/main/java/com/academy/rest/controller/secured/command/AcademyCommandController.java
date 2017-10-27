package com.academy.rest.controller.secured.command;


import com.academy.core.command.AddAcademyCommand;
import com.academy.core.command.AddSectionToAcademyCommand;
import com.academy.core.command.result.AddAcademyResult;
import com.academy.core.command.result.AddSectionResult;
import com.academy.core.command.service.CommandService;
import com.academy.core.domain.Address;
import com.academy.core.domain.Member;
import com.academy.core.dto.SectionBean;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.MemberRepository;
import com.academy.repository.SectionRepository;
import com.academy.rest.api.Academy;
import com.academy.rest.api.Section;
import com.academy.security.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private MemberRepository memberRepository;



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
//
//    @RequestMapping(method = RequestMethod.POST, path = "/read")
//    @ResponseBody
//    @ApiOperation(value = "get all requested users")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "")
//    })
//    public String read() {
//
//        ApplicationContext context = new ClassPathXmlApplicationContext();
//        Resource templateResource = context.getResource("bjj.json");
//
//        StringBuilder emailTemplate = new StringBuilder();
//
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(templateResource.getInputStream()));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                emailTemplate.append(line);
//            }
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, String> map = mapper.readValue(emailTemplate.toString(), HashMap.class);
//            List<Member> members = new ArrayList<>();
//            com.academy.core.domain.Section sec = sectionRepository.findByName("BJJ");
//            List<com.academy.core.domain.Section> secs = new ArrayList<>();
//            secs.add(sec);
//            for(Object value : map.values()) {
//                log.info(value.toString());
//                Member member = new Member();
//                Map val = (Map) value;
//                member.setAcademyName("BJJAcademy.lv");
//                member.setFirstName((String)val.get("firstname"));
//                member.setLastName((String)val.get("lastname"));
//                Map contactInfo = (Map) val.get("contactinfo");
//                member.setEmail((String)contactInfo.get("email"));
//                Address address = new Address();
//                address.setCity((String)contactInfo.get("city"));
//                address.setStreet((String)contactInfo.get("street"));
//                address.setCountry("Latvia");
//                member.setAddress(address);
//                member.setPhone((String)contactInfo.get("phone"));
//                Map classInfo = (Map) val.get("classinfo");
//                member.setInitialClassCount(Long.valueOf((String)classInfo.get("classesattended")));
//
//                member.setSections(secs);
//                members.add(member);
//            }
//            memberRepository.save(members);
//            log.info("map");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return emailTemplate.toString();
//
//    }

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
