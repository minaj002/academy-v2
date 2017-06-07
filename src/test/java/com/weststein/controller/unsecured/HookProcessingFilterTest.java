package com.weststein.controller.unsecured;

import org.junit.Test;

public class HookProcessingFilterTest {
    @Test
    public void calculateRFC2104HMAC() throws Exception {

        HooksController.calculateRFC2104HMAC("12345678".getBytes(), "key", "sha256");

    }

}