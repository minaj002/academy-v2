package com.weststein.handler.card;

import com.ibm.icu.text.Transliterator;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardInquiry;
import com.weststein.integration.request.CardIssue;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import com.weststein.integration.response.AccountAPIv2CardIssue;
import com.weststein.repository.*;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrivateCardRequestHandler {
    private static final String cardStyle = "01";
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private PPFService<CardIssue, AccountAPIv2CardIssue> ppfServiceCardIssue;
    @Autowired
    private PPFService<CardInquiry, AccountAPIv2CardInfo> ppfServiceCardInquiry;
    @Autowired
    private UserService userService;
    private Transliterator transliterator = Transliterator.getInstance("Any-Latin;Latin-ASCII");
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public UserInformation handle() {

        String email = userService.getCurrentUser();
        UserCredentials credentials = userService.getCurrentUserCredentials();
        UserRole privateRole = credentials.getRoles().stream().filter(userRole -> Role.PRIVATE.equals(userRole.getRole())).findFirst().get();
        UserInformation userInformation = userInformationRepository.findOne(privateRole.getEntityId());
        CardIssue cardRequest = createCardRequest(userInformation, email);
        AccountAPIv2CardIssue issueResponse = ppfServiceCardIssue.get(cardRequest, AccountAPIv2CardIssue.class);

        AccountAPIv2CardInfo cardInfoResponse = ppfServiceCardInquiry.get(CardInquiry.builder().cardholderId(issueResponse.getCardIssue().getCardHolderId()).build(),
                AccountAPIv2CardInfo.class);

        CardholderId cardHolderId = cardholderIdRepository.save(new CardholderId(null, issueResponse.getCardIssue().getCardHolderId(), cardInfoResponse.getCardInquiry().getCardInfo().getIban()));

        userInformation.getCardholderIds().add(cardHolderId);
        userInformationRepository.save(userInformation);

        userCredentialRepository.save(credentials);
        emailSender.sendCardEmail(email, credentials.getUserProfile().getLanguage().name().toLowerCase());
        return userInformation;
    }

    private CardIssue createCardRequest(UserInformation application, String email) {
        CardIssue cardRequest = objectMapper.map(application, CardIssue.class);

        cardRequest.setAddress1(toTransilte(application.getAddress().getLine1()));
        cardRequest.setAddress2(toTransilte(application.getAddress().getLine2()));
        cardRequest.setBin("59991198");
        cardRequest.setVerifyDOBOverride("Y");
        cardRequest.setVerifySSNOverride("Y");
        cardRequest.setGeoIPCheckOverride("Y");
        cardRequest.setUserDefinedField3("SOLO");
        cardRequest.setUserDefinedField2("1");
        cardRequest.setCardStyle(cardStyle);
        cardRequest.setDistributorCode("723");
        cardRequest.setEmail(email);
        return cardRequest;
    }

    protected String toTransilte(String from) {
        return transliterator.transliterate(from);

    }

}
