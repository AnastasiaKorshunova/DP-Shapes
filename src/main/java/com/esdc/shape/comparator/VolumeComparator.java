package com.esdc.shape.comparator;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.warehouse.Warehouse;

import java.util.Comparator;

public class VolumeComparator implements Comparator<Pyramid> {
    @Override
    public int compare(Pyramid p1, Pyramid p2) {
        double v1 = Warehouse.getInstance().getProperties(p1.getPyramidId()).getVolume();
        double v2 = Warehouse.getInstance().getProperties(p2.getPyramidId()).getVolume();
        return Double.compare(v1, v2);
    }
}

