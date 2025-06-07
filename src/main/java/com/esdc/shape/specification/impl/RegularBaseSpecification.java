package com.esdc.shape.specification.impl;

import com.esdc.shape.warehouse.PyramidProperties;

public class RegularBaseSpecification extends WarehouseSpecification {

    @Override
    protected boolean check(PyramidProperties props) {
        return props.isBaseRegular();
    }
}
