package com.esdc.shape.specification.impl;

import com.esdc.shape.warehouse.PyramidProperties;

public class HeightBetweenSpecification extends WarehouseSpecification {
    private final double min;
    private final double max;

    public HeightBetweenSpecification(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean check(PyramidProperties props) {
        double height = props.getHeight();
        boolean result = height >= min && height <= max;
        logger.debug("Height check: value = {}, expected in [{}, {}] -> {}", height, min, max, result);
        return result;
    }
}
