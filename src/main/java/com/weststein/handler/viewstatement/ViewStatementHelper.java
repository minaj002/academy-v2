package com.weststein.handler.viewstatement;

import com.weststein.integration.response.AccountAPIv2ViewStatement;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class ViewStatementHelper {

    @Cacheable(keyGenerator = "statementKeyGenerator")
    public AccountAPIv2ViewStatement getSome(String id, LocalDate start, LocalDate end, int top, int page) {


        return null;
    }




}
