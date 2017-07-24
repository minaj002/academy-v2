package com.weststein.controller.secured;

import com.weststein.controller.secured.model.business.*;
import com.weststein.handler.business.*;
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
    @Autowired
    private CreateBusinessProfileHandler createBusinessProfileHandler;
    @Autowired
    private CreateCardIbanHandler createCardIbanHandler;
    @Autowired
    private CreateBankAccountDetailsHandler createBankAccountDetailsHandler;
    @Autowired
    private CreateProjectedLoadingFiguresHandler createProjectedLoadingFiguresHandler;

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

    @PostMapping("/api/business/{businessId}/application/business-profile")
    @ApiOperation(value = "Create business profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void businessProfile(@PathVariable Long businessId, @RequestBody BusinessProfileModel businessProfileModel) {
        createBusinessProfileHandler.handle(businessId, businessProfileModel);
    }

    @PostMapping("/api/business/{businessId}/application/card-iban")
    @ApiOperation(value = "Create card iban")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void cardIban(@PathVariable Long businessId, @RequestBody CardIbanModel cardIbanModel) {
        createCardIbanHandler.handle(businessId, cardIbanModel);
    }

    @PostMapping("/api/business/{businessId}/application/bank-account-details")
    @ApiOperation(value = "Create bank account details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void bankAccountDetails(@PathVariable Long businessId, @RequestBody BankAccountDetailsModel bankAccountDetailsModel) {
        createBankAccountDetailsHandler.handle(businessId, bankAccountDetailsModel);
    }

    @PostMapping("/api/business/{businessId}/application/projected-loading-figures")
    @ApiOperation(value = "Create projected loading figures")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public void projectedLoadingFigures(@PathVariable Long businessId, @RequestBody ProjectedLoadingFiguresModel projectedLoadingFiguresModel) {
        createProjectedLoadingFiguresHandler.handle(businessId, projectedLoadingFiguresModel);
    }

}