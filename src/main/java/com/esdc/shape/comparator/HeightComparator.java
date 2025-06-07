package com.esdc.shape.comparator;


import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.warehouse.Warehouse;

import java.util.Comparator;

public class HeightComparator implements Comparator<Pyramid> {
    @Override
    public int compare(Pyramid p1, Pyramid p2) {
        double h1 = Warehouse.getInstance().getProperties(p1.getPyramidId()).getHeight();
        double h2 = Warehouse.getInstance().getProperties(p2.getPyramidId()).getHeight();
        return Double.compare(h1, h2);
    }
}

