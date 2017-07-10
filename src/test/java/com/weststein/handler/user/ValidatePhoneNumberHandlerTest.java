package com.weststein.handler.user;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatePhoneNumberHandlerTest {
    @Test
    public void generateCode() throws Exception {

        ValidatePhoneNumberHandler handler = new ValidatePhoneNumberHandler();

        assertTrue(handler.generateCode().length() >= 5);

    }

}