package com.weststein.security.auth.hook;

import org.junit.Test;

public class HookProcessingFilterTest {
    @Test
    public void calculateRFC2104HMAC() throws Exception {


        HookProcessingFilter.calculateRFC2104HMAC("12345678".getBytes(), "key", "sha256");


    }

}