package com.weststein.handler.card;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@Ignore
public class CardRequestHandlerTest {

    PrivateCardRequestHandler handler = new PrivateCardRequestHandler();

    @Test
    public void testToTranslite() throws Exception {

        String result = handler.toTransilte("SākumsMazā sadzīves tehnikaMājas tehnika");
        assertEquals("SakumsMaza sadzives tehnikaMajas tehnika", result);

    }
    @Test
    public void testToTransliteKirilic() throws Exception {

        String result = handler.toTransilte("Гулбене и район");
        assertEquals("Gulbene i rajon", result);

    }

}