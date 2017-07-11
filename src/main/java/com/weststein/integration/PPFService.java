package com.weststein.integration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import com.weststein.integration.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class PPFService <T,R>{

    @Autowired
    private PFSResource applicationResource;

    private static Map<Class, String> classToMethodMap = new HashMap<>();
    static {
        classToMethodMap.put(AccountAPIv2ViewStatement.class, "ViewStatement");
        classToMethodMap.put(AccountAPIv2CardIssue.class, "CardIssue");
        classToMethodMap.put(AccountAPIv2CardInfo.class, "CardInquiry");
        classToMethodMap.put(AccountAPIv2UpdateCard.class, "UpdateCard");
        classToMethodMap.put(AccountAPIv2DepositToCard.class, "DepositToCard");
        classToMethodMap.put(AccountAPIv2SepaPayment.class, "BankPayment");
        classToMethodMap.put(AccountAPIv2CardToCard.class, "CardToCard");
        classToMethodMap.put(AccountAPIv2PinReminder.class, "PinReminder");
    }

    public R get(T req, Class<R> clazz) {

        XmlMapper mapper = new XmlMapper();
        R result;
        try {
            log.info(mapper.writeValueAsString(req));

            LinkedHashMap resp = applicationResource.get(mapper.writeValueAsString(req), classToMethodMap.get(clazz));
            String resp2 = (String)resp.get("");
            result = mapper.readValue(resp2, clazz);
            if(!((AccountAPIv2)result).getErrorCode().equals("0000")) {
                List validationErrors = new ArrayList();
                validationErrors.add(ValidationError.builder().message(((AccountAPIv2)result).getDescription()).build());
                throw new ValidationException(validationErrors, "error from pfs");
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
