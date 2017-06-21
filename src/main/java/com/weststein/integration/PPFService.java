package com.weststein.integration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.weststein.integration.response.AccountAPIv2CardInfo;
import com.weststein.integration.response.AccountAPIv2CardIssue;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class PPFService <T,R>{

    @Autowired
    private ApplicationResource applicationResource;

    private static Map<Class, String> classToMethodMap = new HashMap<>();
    static {
        classToMethodMap.put(AccountAPIv2ViewStatement.class, "ViewStatement");
        classToMethodMap.put(AccountAPIv2CardIssue.class, "CardIssue");
        classToMethodMap.put(AccountAPIv2CardInfo.class, "CardInquiry");
    }

    public R get(T req, Class<R> clazz) {

        XmlMapper mapper = new XmlMapper();
        R result;
        try {
            log.info(mapper.writeValueAsString(req));

            LinkedHashMap resp = applicationResource.get(mapper.writeValueAsString(req), classToMethodMap.get(clazz));
            String resp2 = (String)resp.get("");
            result = mapper.readValue(resp2, clazz);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
