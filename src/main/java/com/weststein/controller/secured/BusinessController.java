package com.weststein.controller.secured;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.model.business.AuthorizedUser;
import com.weststein.controller.secured.model.business.BusinessInformationModel;
import com.weststein.handler.business.DeleteRoleHandler;
import com.weststein.handler.business.GetAuthorizedUsersHandler;
import com.weststein.handler.business.GetBusinessInformationHandler;
import com.weststein.infrastructure.MessageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    private MessageBean messageBean;

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