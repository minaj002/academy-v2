package com.weststein.handler.user;

import com.weststein.integration.sms.SmsService;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import com.weststein.repository.UserRole;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
@Slf4j
public class ValidatePhoneNumberHandler {

    private SecureRandom random = new SecureRandom();

    @Autowired
    private UserService userService;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private SmsService smsService;

    public void handle() {

        UserCredentials credentials = userService.getCurrentUserCredentials();
        UserRole privateRole = credentials.getRoles().stream().filter(userRole -> Role.PRIVATE.equals(userRole.getRole())).findFirst().get();
        UserInformation userInfo = userInformationRepository.findOne(privateRole.getEntityId());
        String phone = credentials.getUserProfile().getPhone();
        String code = generateCode();
        userInfo.setPhoneVerificationCode(code);
        userInformationRepository.save(userInfo);
        smsService.send(phone, code, credentials.getUserProfile().getLanguage().name());

    }

    protected String generateCode() {
        String code = new BigInteger(17, random).remainder(BigInteger.valueOf(99999)).toString(10);
        if(code.length() < 6) {
            return code;
        } else {
            return code.substring(0,5);
        }
    }


}
