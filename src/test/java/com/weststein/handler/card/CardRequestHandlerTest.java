package com.weststein.handler.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardRequestHandlerTest {

    CardRequestHandler handler = new CardRequestHandler();

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