package com.weststein.pdf;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PDFServiceItextTest {
    @Test
    public void fill() throws Exception {

        PDFServiceItext itext = new PDFServiceItext();

        itext.fill(new HashMap<>());

    }

}