package com.esdc.shape.validation;

import com.esdc.shape.entity.Point;
import com.esdc.shape.exception.InvalidPyramidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApexValidatorTest {

    private final List<Point> base = Arrays.asList(
            new Point(0, 0, 0),
            new Point(1, 0, 0),
            new Point(0, 1, 0)
    );

    @Test
    void returnsTrueWhenApexIsNotInBasePlane() {
        Point apex = new Point(0, 0, 1);

        assertTrue(ApexValidator.isApexNotInBasePlane(apex, base));
    }

    @Test
    void returnsFalseWhenApexIsInBasePlane() {
        Point apexInPlane = new Point(0.2, 0.2, 0);

        assertFalse(ApexValidator.isApexNotInBasePlane(apexInPlane, base));
    }

    @Test
    void throwsExceptionWhenBaseHasLessThanThreePoints() {
        List<Point> invalidBase = Arrays.asList(new Point(0, 0, 0), new Point(1, 0, 0));

        assertThrows(InvalidPyramidException.class,
                () -> ApexValidator.isApexNotInBasePlane(new Point(0, 0, 1), invalidBase));
    }

    @Test
    void throwsExceptionWhenBaseIsNull() {
        assertThrows(InvalidPyramidException.class,
                () -> ApexValidator.isApexNotInBasePlane(new Point(0, 0, 1), null));
    }
}
