package com.esdc.shape.comparator;

import com.esdc.shape.entity.Pyramid;

import java.util.Comparator;

public class IdComparator implements Comparator<Pyramid> {
    @Override
    public int compare(Pyramid p1, Pyramid p2) {
        return Long.compare(p1.getPyramidId(), p2.getPyramidId());
    }
}
