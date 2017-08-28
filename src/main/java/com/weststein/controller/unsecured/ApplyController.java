package com.weststein.controller.unsecured;

import com.weststein.controller.unsecured.model.ApplicationModel;
import com.weststein.controller.unsecured.model.BusinessApplicationModel;
import com.weststein.handler.application.ApplicationHandler;
import com.weststein.handler.application.BusinessApplicationHandler;
import com.weststein.repository.*;
import com.weststein.security.model.entity.Role;
import com.weststein.validator.EmailValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApplyController {

    @Autowired
    private ApplicationHandler applicationHandler;
    @Autowired
    private BusinessApplicationHandler businessApplicationHandler;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping("/api/apply/attach")
    @ApiOperation(value = "test resource To Be removed")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void attach() {

        CardholderId cardholderId = CardholderId.builder().cardholderId("400000626035").build();
        cardholderId = cardholderIdRepository.save(cardholderId);

        UserCredentials cred = userCredentialRepository.findUserCredentialsByEmailAndStatusNot("jevgenijs.minajevs@weststeincard.com", UserCredentials.Status.DELETED).get();
        UserRole userRole = new UserRole();
        Role role = Role.PRIVATE;
        userRole.setRole(role);
        userRole.setEntityId(cardholderId.getId());
        userRole.setRoleType(UserRole.RoleType.PRIVATE);
        userRoleRepository.save(userRole);
        cred.getRoles().add(userRole);
        userCredentialRepository.save(cred);
    }

    @PostMapping("/api/apply/personal")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void applyPersonal(@Valid @RequestBody @DateTimeFormat(pattern = "ddMMyyyy") ApplicationModel application) {
        applicationHandler.handle(application);
    }

    @PostMapping("/api/apply/business")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void applyBusiness(@Valid @RequestBody @DateTimeFormat(pattern = "ddMMyyyy") BusinessApplicationModel application) {
        businessApplicationHandler.handle(application);
    }

    @PostMapping("/api/apply/verify")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @ResponseStatus(HttpStatus.OK)
    public void validateEmail(@Valid @Email @RequestParam String email) {
        emailValidator.validate(email);
    }


}
