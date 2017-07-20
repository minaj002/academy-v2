package com.weststein.handler.card;

import com.ibm.icu.text.Transliterator;
import com.netflix.config.validation.ValidationException;
import com.weststein.email.EmailSender;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.CardIssue;
import com.weststein.integration.response.AccountAPIv2CardIssue;
import com.weststein.repository.*;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private PPFService<CardIssue, AccountAPIv2CardIssue> ppfService1;
    @Autowired
    private UserService userService;
    private Transliterator transliterator = Transliterator.getInstance("Any-Latin;Latin-ASCII");
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public UserInformation handle() {

        String email = userService.getCurrentUser();
        UserInformation userInformation = userInformationRepository.findByEmail(email);
        CardIssue cardRequest = createCardRequest(userInformation);
        AccountAPIv2CardIssue res2 = ppfService1.get(cardRequest, AccountAPIv2CardIssue.class);
        UserCredentials credentials = userCredentialRepository.findUserCredentialsByEmail(email).orElseThrow(() -> new ValidationException("No such user"));
        CardholderId cardHolderId = cardholderIdRepository.save(CardholderId.builder().cardholderId(res2.getCardIssue().getCardHolderId()).build());

        List<UserRole> roles = credentials.getRoles();
        List<UserRole> rolesWithoutNew = roles.stream().filter(userRole -> Role.NEW.equals(userRole.getRole())).collect(Collectors.toList());
        rolesWithoutNew.add(UserRole.builder().entityId(cardHolderId.getId()).roleType(UserRole.RoleType.PRIVATE).role(Role.PRIVATE).build());
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
