package com.esdc.shape.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void getters() {
        Point p = new Point(1, 2, 3);
        assertAll(
                () -> assertEquals(1, p.getX(), 1e-6),
                () -> assertEquals(2, p.getY(), 1e-6),
                () -> assertEquals(3, p.getZ(), 1e-6)
        );
    }

    @Test
    void equalsAndHash() {
        Point a = new Point(1, 2, 3);
        Point b = new Point(1, 2, 3);
        Point c = new Point(3, 2, 1);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void toStringFormat() {
        Point p = new Point(1.234, 2.345, 3.456);
        assertEquals("Point(x=1.23, y=2.35, z=3.46)", p.toString());
    }
}
