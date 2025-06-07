package com.esdc.shape.comparator;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.warehouse.Warehouse;

import java.util.Comparator;

public class AreaComparator implements Comparator<Pyramid> {
    @Override
    public int compare(Pyramid p1, Pyramid p2) {
        double a1 = Warehouse.getInstance().getProperties(p1.getPyramidId()).getArea();
        double a2 = Warehouse.getInstance().getProperties(p2.getPyramidId()).getArea();
        return Double.compare(a1, a2);
    }
}

