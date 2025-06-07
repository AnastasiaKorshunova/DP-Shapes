package com.esdc.shape.comparator;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.entity.PyramidType;

import java.util.Comparator;

public class TypeComparator implements Comparator<Pyramid> {
    @Override
    public int compare(Pyramid p1, Pyramid p2) {
        PyramidType t1 = p1.getType();
        PyramidType t2 = p2.getType();
        return t1.name().compareTo(t2.name());
    }
}
