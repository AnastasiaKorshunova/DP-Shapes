package com.esdc.shape.comparator;

import com.esdc.shape.entity.PyramidType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeComparatorTest extends BaseComparatorTest {

    @Test
    void compareByType() {
        var square   = build(1, 3); square.setType(PyramidType.SQUARE);
        var triangle = build(1, 3); triangle.setType(PyramidType.TRIANGLE);
        var unknown  = build(1, 3); unknown.setType(PyramidType.UNKNOWN);

        TypeComparator cmp = new TypeComparator();

        assertTrue(cmp.compare(square, triangle) < 0);
        assertTrue(cmp.compare(triangle, unknown) < 0);
        assertEquals(0, cmp.compare(square, square));
    }
}
