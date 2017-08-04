package com.weststein.handler.business;

import com.weststein.pdf.PDFFieldMapper;
import com.weststein.pdf.PDFServiceItext;
import com.weststein.repository.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class CreateRegistrationPDFHandler {

    @Autowired
    private CardIbanRepository cardIbanRepository;
    @Autowired
    private BusinessProfileRepository businessProfileRepository;
    @Autowired
    private BankAccountDetailsRepository bankAccountDetailsRepository;
    @Autowired
    private CompanyInformationRepository companyInformationRepository;
    @Autowired
    private ShareHolderRepository shareHolderRepository;
    @Autowired
    private RequiredDocumentRepository requiredDocumentRepository;
    @Autowired
    private ProjectedLoadingFiguresRepository projectedLoadingFiguresRepository;
    @Autowired
    private PDFServiceItext pdfServiceItext;

    public ByteArrayOutputStream handle(Long businessId) {

        Map<String, String> map = new HashMap<>();
        PDFFieldMapper.mapCardIban(cardIbanRepository.findByBusinessId(businessId), map);
        PDFFieldMapper.mapBusinessProfile(businessProfileRepository.findByBusinessId(businessId), map);
        PDFFieldMapper.mapBankAccountDetails(bankAccountDetailsRepository.findByBusinessId(businessId), map);
        PDFFieldMapper.mapCompanyInformation(companyInformationRepository.findByBusinessId(businessId), map);
        PDFFieldMapper.mapShareholders(shareHolderRepository.findByBusinessId(businessId), map);
        PDFFieldMapper.mapRequiredDocuments(requiredDocumentRepository.findByBusinessId(businessId), map);
        PDFFieldMapper.mapProjectedLoadingFigures(projectedLoadingFiguresRepository.findByBusinessId(businessId), map);

        return pdfServiceItext.fill(map);
    }

}
