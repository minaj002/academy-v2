package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.pdf.PDFServiceItext;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import com.weststein.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Component
public class ViewStatementPdfBusinessHandler extends AbstractViewStatementHandler<ByteArrayOutputStream> {

    @Autowired
    private PDFServiceItext pdfServiceItext;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public ByteArrayOutputStream handle(Long businessId, Long cardholderId, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {

        BusinessInformation businessInformation = businessInformationRepository.findOne(businessId);
        CardholderId cardholderIdObject = cardholderIdRepository.findOne(cardholderId);
        ViewStatementModel model = getStatement(cardholderId, from, to, type, size, page);
        ByteArrayOutputStream res = pdfServiceItext.createStatement(businessInformation.getEnterpriseName(), cardholderIdObject.getIban(), model);

        return res;
    }

}
