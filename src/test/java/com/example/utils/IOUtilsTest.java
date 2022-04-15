package com.example.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IOUtilsTest {

    @Test
    void isStringContainsOnlyNumbers() {
        String onlyNumbers = "1234567890";
        assertTrue(IOUtils.isStringContainsOnlyNumbers(onlyNumbers));

        String strWithCharAndNumbers = "12abc678gh";
        assertFalse(IOUtils.isStringContainsOnlyNumbers(strWithCharAndNumbers));
    }
}