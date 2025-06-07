package com.esdc.shape.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointFormatValidatorTest {
    @Test
    void returnsTrueForValidPointString() {
        assertTrue(PointFormatValidator.isValid("1.0,2.5,3.7"));
        assertTrue(PointFormatValidator.isValid("  -1.2 , 0 , 4.56  "));
    }

    @Test
    void returnsFalseForNullOrBlankInput() {
        assertFalse(PointFormatValidator.isValid(null));
        assertFalse(PointFormatValidator.isValid("   "));
    }

    @Test
    void returnsFalseWhenCoordinateCountIsNotThree() {
        assertFalse(PointFormatValidator.isValid("1,2"));
        assertFalse(PointFormatValidator.isValid("1,2,3,4"));
    }

    @Test
    void returnsFalseWhenAnyCoordinateIsNotNumeric() {
        assertFalse(PointFormatValidator.isValid("1.0,Na,3.0"));
        assertFalse(PointFormatValidator.isValid("a,b,c"));
    }
}
