package com.weststein.handler.user;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@Ignore
public class ValidatePhoneNumberHandlerTest {
    @Test
    public void generateCode() throws Exception {

        ValidatePhoneNumberHandler handler = new ValidatePhoneNumberHandler();

        assertTrue(handler.generateCode().length() <= 5);

    }

}