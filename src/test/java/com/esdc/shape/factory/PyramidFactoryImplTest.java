package com.esdc.shape.factory;

import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.exception.InvalidPyramidException;
import com.esdc.shape.factory.impl.PyramidFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PyramidFactoryImplTest {
    private final PyramidFactoryImpl factory = new PyramidFactoryImpl();

    @Test
    void createPyramid_validInput_returnsPyramid() {
        Point apex = new Point(0, 0, 1);
        List<Point> base = List.of(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        );
        Pyramid pyramid = factory.createPyramid(apex, base);
        assertNotNull(pyramid);
        assertEquals(apex, pyramid.getApex());
        assertIterableEquals(base, pyramid.getBase());
    }

    @Test
    void createPyramid_invalidBase_throwsException() {
        Point apex = new Point(0, 0, 1);
        List<Point> tooSmallBase = List.of(
                new Point(0, 0, 0),
                new Point(1, 0, 0)
        );
        assertThrows(InvalidPyramidException.class, () -> factory.createPyramid(apex, tooSmallBase));
        assertThrows(InvalidPyramidException.class, () -> factory.createPyramid(apex, null));
    }
}
