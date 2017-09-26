package com.weststein.controller.secured.business;

import com.weststein.controller.ResponseWrapper;
import com.weststein.controller.secured.business.model.business.*;
import com.weststein.handler.business.*;
import com.weststein.infrastructure.MessageBean;
import com.weststein.repository.business.RequiredDocument;
import com.weststein.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

@RestController
public class BusinessRegistrationController {

    @Autowired
    private UserService userService;
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
    @Autowired
    private RequiredDocumentsUploadHandler requiredDocumentsUploadHandler;
    @Autowired
    private CreateRegistrationPDFHandler createRegistrationPDFHandler;
    @Autowired
    private MessageBean messageBean;

    @PostMapping("/api/business/{businessId}/application/company-info")
    @ApiOperation(value = "Create company info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper companyInfo(@PathVariable Long businessId, @RequestBody CompanyInformationModel companyInfo) {
        userService.isAuthorizedForBusiness(businessId);
        createCompanyInfoHandler.handle(businessId, companyInfo);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @PostMapping("/api/business/{businessId}/application/company-structure")
    @ApiOperation(value = "Create company structure")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper companyStructure(@PathVariable Long businessId, @RequestBody CompanyStructureModel companyStructure) {
        userService.isAuthorizedForBusiness(businessId);
        createCompanyStructureHandler.handle(businessId, companyStructure);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @PostMapping("/api/business/{businessId}/application/business-profile")
    @ApiOperation(value = "Create business profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper businessProfile(@PathVariable Long businessId, @RequestBody BusinessProfileModel businessProfileModel) {
        userService.isAuthorizedForBusiness(businessId);
        createBusinessProfileHandler.handle(businessId, businessProfileModel);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @PostMapping("/api/business/{businessId}/application/card-iban")
    @ApiOperation(value = "Create card iban")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper cardIban(@PathVariable Long businessId, @RequestBody CardIbanModel cardIbanModel) {
        userService.isAuthorizedForBusiness(businessId);
        createCardIbanHandler.handle(businessId, cardIbanModel);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @PostMapping("/api/business/{businessId}/application/bank-account-details")
    @ApiOperation(value = "Create bank account details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper bankAccountDetails(@PathVariable Long businessId, @RequestBody BankAccountDetailsModel bankAccountDetailsModel) {
        userService.isAuthorizedForBusiness(businessId);
        createBankAccountDetailsHandler.handle(businessId, bankAccountDetailsModel);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @PostMapping("/api/business/{businessId}/application/projected-loading-figures")
    @ApiOperation(value = "Create projected loading figures")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper projectedLoadingFigures(@PathVariable Long businessId, @RequestBody ProjectedLoadingFiguresModel projectedLoadingFiguresModel) {
        userService.isAuthorizedForBusiness(businessId);
        createProjectedLoadingFiguresHandler.handle(businessId, projectedLoadingFiguresModel);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @PostMapping("/api/business/{businessId}/application/required-documents")
    @ApiOperation(value = "Upload required for the new business documents", consumes = "multipart/form-data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper requiredDocumentsUpload(@PathVariable Long businessId, @RequestParam("file") MultipartFile file, @RequestParam("type") RequiredDocument.Type type
    ) {
        userService.isAuthorizedForBusiness(businessId);
        requiredDocumentsUploadHandler.handle(businessId, file, type);
        return ResponseWrapper.builder()
                .messages(messageBean.getMessages())
                .build();
    }

    @GetMapping(value = "/api/business/{businessId}/pdf", produces = "application/pdf")
    @ApiOperation(value = "Download created application as pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Resource getPdf(@PathVariable Long businessId) {
        ByteArrayOutputStream res = createRegistrationPDFHandler.handle(businessId);
        ByteArrayResource resource = new ByteArrayResource(res.toByteArray());
        return resource;
    }

}