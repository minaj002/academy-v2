package com.weststein.integration.sms;

import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("prod")
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsResource smsResource;
    @Autowired
    private SmsTextSource smsTextSource;

    @Override
    public void send(String number, String code, String language) {

        String smsText = String.format(smsTextSource.getSms(language), code);
        SmsResponse result = smsResource.send(number, smsText);
        if (!StringUtils.isEmpty(result.getError())) {
            List<ValidationError> errors = new ArrayList();
            errors.add(ValidationError.builder().field("phone").message(result.getError()).build());
            throw new ValidationException(errors, result.getError());
        }
    }
}
