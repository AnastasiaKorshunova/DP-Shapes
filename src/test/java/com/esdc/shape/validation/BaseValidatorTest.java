package com.esdc.shape.validation;

import com.esdc.shape.entity.Point;
import com.esdc.shape.exception.InvalidPyramidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseValidatorTest {

    @Test
    void returnsTrueWhenAllPointsCoplanar() {
        List<Point> coplanar = Arrays.asList(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0)
        );

        assertTrue(BaseValidator.validateCoplanarPoints(coplanar));
    }

    @Test
    void returnsFalseWhenNonCoplanarPointPresent() {
        List<Point> nonCoplanar = Arrays.asList(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        assertFalse(BaseValidator.validateCoplanarPoints(nonCoplanar));
    }

    @Test
    void throwsExceptionWhenLessThanThreePoints() {
        List<Point> tooFew = Arrays.asList(
                new Point(0, 0, 0),
                new Point(1, 0, 0)
        );

        assertThrows(InvalidPyramidException.class,
                () -> BaseValidator.validateCoplanarPoints(tooFew));
    }

    @Test
    void throwsExceptionWhenPointsNull() {
        assertThrows(InvalidPyramidException.class,
                () -> BaseValidator.validateCoplanarPoints(null));
    }
}

