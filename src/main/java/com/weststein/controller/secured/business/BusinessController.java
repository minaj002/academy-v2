package com.weststein.controller.secured.business;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.business.model.business.AuthorizedUser;
import com.weststein.controller.secured.business.model.business.BusinessInformationModel;
import com.weststein.handler.business.DeleteRoleHandler;
import com.weststein.handler.business.GetAuthorizedUsersHandler;
import com.weststein.handler.business.GetBusinessInformationHandler;
import com.weststein.handler.business.UpdateBusinessInformationHandler;
import com.weststein.infrastructure.MessageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private GetAuthorizedUsersHandler getAuthorizedUsersHandler;
    @Autowired
    private DeleteRoleHandler deleteRoleHandler;
    @Autowired
    private GetBusinessInformationHandler getBusinessInformationHandler;
    @Autowired
    private UpdateBusinessInformationHandler updateBusinessInformationHandler;
    @Autowired
    private MessageBean messageBean;

    @PreAuthorize("hasPermission(#businessId,'OWNER')")
    @GetMapping(value = "/api/business/{businessId}/authorized-users")
    @ApiOperation(value = "get authorized users for this business")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<AuthorizedUser>> getAuthorizedUsers(@PathVariable Long businessId) {

        return ResponseWrapper.<List<AuthorizedUser>>builder()
                .response(getAuthorizedUsersHandler.handle(businessId))
                .messages(messageBean.getMessages())
                .build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER,BOOKKEEPER,FINANCIAL_OFFICER')")
    @GetMapping(value = "/api/business/{businessId}")
    @ApiOperation(value = "get business information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<BusinessInformationModel> getBusinessInformation(@PathVariable Long businessId) {

        return ResponseWrapper.<BusinessInformationModel>builder()
                .response(getBusinessInformationHandler.handle(businessId))
                .messages(messageBean.getMessages())
                .build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER')")
    @PutMapping(value = "/api/business/{businessId}")
    @ApiOperation(value = "get business information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<BusinessInformationModel> updateBusinessInformation(@PathVariable Long businessId, BusinessInformationModel businessInformationModel) {

        return ResponseWrapper.<BusinessInformationModel>builder()
                .response(updateBusinessInformationHandler.handle(businessId, businessInformationModel))
                .messages(messageBean.getMessages())
                .build();
    }

    @PreAuthorize("hasPermission(#businessId,'OWNER')")
    @DeleteMapping(value = "/api/business/{businessId}/remove-role/{roleId}")
    @ApiOperation(value = "remove role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper removeRole(@PathVariable Long businessId, @PathVariable Long roleId) {
        deleteRoleHandler.handle(businessId, roleId);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

}