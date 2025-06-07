package com.esdc.shape.comparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaComparatorTest extends BaseComparatorTest {

    @Test
    void compareByArea() {
        var narrow = build(1, 3);   // основание S≈0.433
        var wide   = build(2, 3);   // основание S≈1.732

        AreaComparator cmp = new AreaComparator();

        assertTrue(cmp.compare(narrow, wide) < 0);
        assertTrue(cmp.compare(wide, narrow) > 0);
        assertEquals(0, cmp.compare(wide, wide));
    }
}
