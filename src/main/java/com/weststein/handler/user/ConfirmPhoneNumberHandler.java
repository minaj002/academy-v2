package com.weststein.handler.user;

import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.repository.UserCredentials;
import com.weststein.repository.UserInformation;
import com.weststein.repository.UserInformationRepository;
import com.weststein.repository.UserRole;
import com.weststein.security.UserService;
import com.weststein.security.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ConfirmPhoneNumberHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Transactional
    public void handle(String code) {

        UserCredentials credentials = userService.getCurrentUserCredentials();
        UserRole privateRole = credentials.getRoles().stream().filter(userRole -> Role.PRIVATE.equals(userRole.getRole())).findFirst().get();
        UserInformation userInfo = userInformationRepository.findOne(privateRole.getEntityId());
        String verificationCode = userInfo.getPhoneVerificationCode();
        if(StringUtils.isEmpty(verificationCode)) {
            List<ValidationError> errors = new ArrayList();
            errors.add(ValidationError.builder().field("code").message("Phone number is already verified").build());
            throw new ValidationException(errors, "Phone number is already verified");
        }
        if (!verificationCode.equals(code)) {
            List<ValidationError> errors = new ArrayList();
            errors.add(ValidationError.builder().field("code").message("Invalid verification code").build());
            throw new ValidationException(errors, "Invalid verification code");
        }

        userInfo.setPhoneVerificationCode(null);
        userInfo.setPhoneVerified(true);
        userInformationRepository.save(userInfo);

    }

}
