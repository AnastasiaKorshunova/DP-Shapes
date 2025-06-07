package com.esdc.shape.warehouse;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PyramidPropertiesTest {

    @Test
    void gettersReturnConstructorValues() {
        PyramidProperties props = new PyramidProperties(100.0, 150.0, 20.0, true, null);

        assertEquals(100.0, props.getVolume());
        assertEquals(150.0, props.getArea());
        assertEquals(20.0, props.getHeight());
        assertTrue(props.isBaseRegular());
        assertNull(props.getType());
    }

    @Test
    void equalsAndHashCodeFollowContract() {
        PyramidProperties p1 = new PyramidProperties(1.0, 2.0, 3.0, false, null);
        PyramidProperties p2 = new PyramidProperties(1.0, 2.0, 3.0, false, null);
        PyramidProperties p3 = new PyramidProperties(1.0, 2.0, 4.0, false, null);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
    }

    @Test
    void toStringContainsFormattedValues() {
        PyramidProperties props = new PyramidProperties(10.5, 15.0, 5.2, false, null);
        String out = props.toString();

        assertTrue(out.contains("volume=10,50"));
        assertTrue(out.contains("area=15,00"));
        assertTrue(out.contains("height=5,20"));
    }
}
