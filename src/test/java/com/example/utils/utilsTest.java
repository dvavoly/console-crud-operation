package com.example.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class utilsTest {

    @Test
    void isStringContainsOnlyNumbers() {
        String onlyNumbers = "1234567890";
        assertTrue(utils.isStringContainsOnlyNumbers(onlyNumbers));

        String strWithCharAndNumbers = "12abc678gh";
        assertFalse(utils.isStringContainsOnlyNumbers(strWithCharAndNumbers));
    }
}