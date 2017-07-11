package com.weststein.handler.user;

import com.weststein.integration.sms.SmsService;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import com.weststein.security.UserService;
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

        UserInformation userInfo = userInformationRepository.findByEmail(userService.getCurrentUser());
        String phone = userInfo.getPhone();
        String code = generateCode();
        String smsText = "Your verification code is " + code + "\nYours sincerely, WestStein";
        userInfo.setPhoneVerificationCode(code);
        userInformationRepository.save(userInfo);
        smsService.send(phone, smsText);

    }

    protected String generateCode() {
        return new BigInteger(17, random).remainder(BigInteger.valueOf(99999)).toString(10);
    }


}
