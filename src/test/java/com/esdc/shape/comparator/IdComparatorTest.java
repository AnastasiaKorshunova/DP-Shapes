package com.esdc.shape.comparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdComparatorTest extends BaseComparatorTest {

    @Test
    void compareById() {
        var first  = build(1, 3);
        var second = build(1, 3);

        IdComparator cmp = new IdComparator();

        assertTrue(cmp.compare(first, second) < 0);
        assertTrue(cmp.compare(second, first) > 0);
        assertEquals(0, cmp.compare(first, first));
    }
}
