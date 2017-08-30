package com.weststein.handler.viewstatement;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.ViewStatement;
import com.weststein.integration.response.AccountAPIv2ViewStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StatementCacheHelper {

    private static String DATE_FORMAT = "yyyy-MM-dd";
    @Autowired
    private PPFService<ViewStatement, AccountAPIv2ViewStatement> ppfService;

    @Cacheable(value = "statement", keyGenerator = "statementKeyGenerator")
    public AccountAPIv2ViewStatement callPfs(String id, LocalDateTime from, LocalDateTime to, boolean useCachedIfExists) {
        ViewStatement object = new ViewStatement();
        object.setCardholderId(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        object.setEndDate(formatter.format(to));
        object.setStartDate(formatter.format(from));
        object.setViewStyle("Y");
        AccountAPIv2ViewStatement res = ppfService.get(object, AccountAPIv2ViewStatement.class);
        return res;
    }

}
