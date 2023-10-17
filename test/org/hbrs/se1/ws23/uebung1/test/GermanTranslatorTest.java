package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        GermanTranslator translator = new GermanTranslator();
        String value1 = translator.translateNumber(0);
        String value2 = translator.translateNumber(5);
        String value3 = translator.translateNumber(11);
        String value4 = translator.translateNumber(1);
        String value5 = translator.translateNumber(10);

        assertEquals(value1, "Übersetzung der Zahl 0 nicht möglich 1.0");
        assertEquals(value2, "fünf");
        assertEquals(value3, "Übersetzung der Zahl 11 nicht möglich 1.0");
        assertEquals(value4, "eins");
        assertEquals(value5, "zehn");
    }
}