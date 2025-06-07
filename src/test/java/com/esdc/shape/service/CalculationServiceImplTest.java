package com.esdc.shape.service;

import com.esdc.shape.entity.Point;
import com.esdc.shape.service.impl.CalculationServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculationServiceImplTest {
    private final CalculationServiceImpl service = CalculationServiceImpl.getInstance();

    private static Point p(double x, double y, double z) {
        return new Point(x, y, z);
    }

    @Test
    void getInstance_returnsSameObject() {
        assertSame(CalculationServiceImpl.getInstance(), CalculationServiceImpl.getInstance(),
                "getInstance() should always return the same singleton instance");
    }


    @Test
    void calculateHeight_returnsZCoordinate() {
        Point apex = p(1, 2, 7);
        assertEquals(7.0, service.calculateHeight(apex), 1e-9,
                "Height should equal the apex Z‑coordinate");
    }

    @Test
    void calculateVolume_usesOneThirdAreaTimesHeight() {
        double area = 12.0;
        Point apex = p(0, 0, 6);
        double expected = area * 6 / 3;
        assertEquals(expected, service.calculateVolume(area, apex), 1e-9,
                "Volume should be area × height ÷ 3");
    }

    @Test
    void calculateArea_triangle() {
        List<Point> base = List.of(p(0, 0, 0), p(4, 0, 0), p(0, 3, 0));
        assertEquals(6.0, service.calculateArea(base), 1e-9,
                "Right‑triangle 4×3 should have area 6");
    }

    @Test
    void calculateArea_rectangle() {
        List<Point> base = List.of(p(0, 0, 0), p(4, 0, 0), p(4, 3, 0), p(0, 3, 0));
        assertEquals(12.0, service.calculateArea(base), 1e-9,
                "Rectangle 4×3 should have area 12");
    }

    @Test
    void calculateArea_regularPentagon() {
        double s = 2.0;
        double R = s / (2 * Math.sin(Math.PI / 5));
        List<Point> base = List.of(
                p(R, 0, 0),
                p(R * Math.cos(2 * Math.PI / 5), R * Math.sin(2 * Math.PI / 5), 0),
                p(R * Math.cos(4 * Math.PI / 5), R * Math.sin(4 * Math.PI / 5), 0),
                p(R * Math.cos(6 * Math.PI / 5), R * Math.sin(6 * Math.PI / 5), 0),
                p(R * Math.cos(8 * Math.PI / 5), R * Math.sin(8 * Math.PI / 5), 0)
        );
        double expectedArea = (5 * s * s) / (4 * Math.tan(Math.PI / 5));
        assertEquals(expectedArea, service.calculateArea(base), 1e-6,
                "Area of a regular pentagon should follow the known formula");
    }

    @Test
    void checkBaseRegularity_equilateralTriangle_returnsTrue() {
        List<Point> base = List.of(
                p(0, 0, 0),
                p(2, 0, 0),
                p(1, Math.sqrt(3), 0)
        );
        assertTrue(service.checkBaseRegularity(base),
                "Equilateral triangle should be recognised as regular");
    }

    @Test
    void checkBaseRegularity_rectangle_returnsFalse() {
        List<Point> base = List.of(
                p(0, 0, 0),
                p(4, 0, 0),
                p(4, 3, 0),
                p(0, 3, 0)
        );
        assertFalse(service.checkBaseRegularity(base),
                "Rectangle with unequal side lengths is not regular");
    }

    @Test
    void checkBaseRegularity_lessThanThreePoints_returnsFalse() {
        List<Point> base = List.of(p(0, 0, 0), p(1, 1, 1));
        assertFalse(service.checkBaseRegularity(base),
                "A base with fewer than 3 points cannot be regular");
    }
}
