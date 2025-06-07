package com.esdc.shape.validation;

import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.state.InvalidPyramidState;
import com.esdc.shape.state.ValidPyramidState;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class PyramidValidatorTest {
    @Test
    void setsValidStateForCorrectPyramid() {
        Pyramid pyramid = new Pyramid(
                new Point(0, 0, 1),
                Arrays.asList(
                        new Point(0, 0, 0),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)
                ));

        PyramidValidator.validateAndSetState(pyramid);

        assertInstanceOf(ValidPyramidState.class, pyramid.getState());
    }

    @Test
    void setsInvalidStateWhenApexCoplanarWithBase() {
        Pyramid pyramid = new Pyramid(new Point(0.2, 0.2, 0),
                Arrays.asList(
                        new Point(0, 0, 0),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)
                ));

        PyramidValidator.validateAndSetState(pyramid);

        assertInstanceOf(InvalidPyramidState.class, pyramid.getState());
    }
}
