package com.esdc.shape.comparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeightComparatorTest extends BaseComparatorTest {

    @Test
    void compareByHeight() {
        var low  = build(1, 2);   // вершина на Z=2
        var high = build(1, 7);   // вершина на Z=7

        HeightComparator cmp = new HeightComparator();

        assertTrue(cmp.compare(low, high) < 0);
        assertTrue(cmp.compare(high, low) > 0);
        assertEquals(0, cmp.compare(high, high));
    }
}
