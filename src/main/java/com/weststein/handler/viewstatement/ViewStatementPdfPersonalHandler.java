package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.pdf.PDFServiceItext;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Component
public class ViewStatementPdfPersonalHandler extends AbstractViewStatementHandler<ByteArrayOutputStream> {

    @Autowired
    private PDFServiceItext pdfServiceItext;
    @Autowired
    private UserService userService;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public ByteArrayOutputStream handle(Long personalId, Long cardHolderId, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {

        UserCredentials userCredentials = userService.getCurrentUserCredentials();
        ViewStatementModel model = getStatement(cardHolderId, from, to, type, size, page);
        CardholderId cardholderIdObject = cardholderIdRepository.findOne(cardHolderId);
        ByteArrayOutputStream res = pdfServiceItext.createStatement(userCredentials.getUserProfile().getFirstName() +" "+ userCredentials.getUserProfile().getLastName(), cardholderIdObject.getIban(), model);

        return res;
    }

}
