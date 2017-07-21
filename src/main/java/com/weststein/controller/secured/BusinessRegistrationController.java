package com.weststein.controller.secured;

import com.weststein.controller.secured.model.CompanyInformationModel;
import com.weststein.controller.secured.model.CompanyStructureModel;
import com.weststein.handler.business.CreateCompanyInfoHandler;
import com.weststein.handler.business.CreateCompanyStructureHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessRegistrationController {

    @Autowired
    private CreateCompanyStructureHandler createCompanyStructureHandler;
    @Autowired
    private CreateCompanyInfoHandler createCompanyInfoHandler;

    @PostMapping("/api/business/{businessId}/application/company-info")
    @ApiOperation(value = "Create company info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void companyInfo(@PathVariable Long businessId, @RequestBody CompanyInformationModel companyInfo) {
        createCompanyInfoHandler.handle(businessId, companyInfo);
    }

    @PostMapping("/api/business/{businessId}/application/company-structure")
    @ApiOperation(value = "Create company structure")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void companyStructure(@PathVariable Long businessId, @RequestBody CompanyStructureModel companyStructure) {
        createCompanyStructureHandler.handle(businessId, companyStructure);
    }

}