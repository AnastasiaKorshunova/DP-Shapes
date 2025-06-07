package com.esdc.shape.comparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeComparatorTest extends BaseComparatorTest {

    @Test
    void compareByVolume() {
        var small = build(1, 2);
        var big   = build(1, 6);

        VolumeComparator cmp = new VolumeComparator();

        assertTrue(cmp.compare(small, big) < 0);
        assertTrue(cmp.compare(big, small) > 0);
        assertEquals(0, cmp.compare(big, big));
    }
}
