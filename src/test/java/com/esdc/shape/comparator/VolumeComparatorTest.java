package com.esdc.shape.comparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeComparatorTest extends BaseComparatorTest {

    @Test
    void compareByVolume() {
        var small = build(1, 2);   // объём меньше
        var big   = build(1, 6);   // объём больше (в 3 раза выше)

        VolumeComparator cmp = new VolumeComparator();

        assertTrue(cmp.compare(small, big) < 0);
        assertTrue(cmp.compare(big, small) > 0);
        assertEquals(0, cmp.compare(big, big));
    }
}
