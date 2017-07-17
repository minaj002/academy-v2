package com.weststein.handler.card;

import com.ibm.icu.text.Transliterator;
import com.netflix.config.validation.ValidationException;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardIssue;
import com.weststein.integration.response.AccountAPIv2CardIssue;
import com.weststein.repository.UserCredentialRepository;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import com.weststein.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class CardRequestHandler {
    private static final String cardStyle = "01";
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private PPFService<CardIssue, AccountAPIv2CardIssue> ppfService1;
    @Autowired
    private UserService userService;
    private Transliterator transliterator = Transliterator.getInstance("Any-Latin;Latin-ASCII");
    @Autowired
    private EmailSender emailSender;

    public UserInformation handle() {

        String email = userService.getCurrentUser();
        UserInformation userInformation = userInformationRepository.findByEmail(email);
        CardIssue cardRequest = createCardRequest(userInformation);
        AccountAPIv2CardIssue res2 = ppfService1.get(cardRequest, AccountAPIv2CardIssue.class);
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmail(email).orElseThrow(() -> new ValidationException("No such user"));
        Set<String> cardholderIds = new HashSet<>();
        cardholderIds.add(res2.getCardIssue().getCardHolderId());
        credentials.setCardHolderIds(cardholderIds);
        userCredentialRepository.save(credentials);
        emailSender.sendCardEmail(email, userInformation.getLanguage().name().toLowerCase());
        return userInformation;
    }

    private CardIssue createCardRequest(UserInformation application) {
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
        return cardRequest;
    }

    protected String toTransilte(String from) {
        return transliterator.transliterate(from);

    }

}
