package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.pdf.PDFServiceItext;
import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Slf4j
@Component
public class GetSepaPaymentAsPDFHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private PDFServiceItext pdfServiceItext;
    @Autowired
    private SepaTransferRepository sepaTransferRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;

    public ByteArrayOutputStream handle(Long businessId, Long id) {

        BusinessInformation businessInformation = businessInformationRepository.findOne(businessId);
        SepaTransfer sepaTransferEntity = sepaTransferRepository.findOne(id);
        ByteArrayOutputStream res = pdfServiceItext.createSepaPayment(businessInformation.getEnterpriseName(), sepaTransferEntity);


        return res;
    }
}
