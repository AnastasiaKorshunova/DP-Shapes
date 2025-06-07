package com.esdc.shape.specification.impl;

import com.esdc.shape.warehouse.PyramidProperties;

public class VolumeGreaterThanSpecification extends WarehouseSpecification {
    private final double minVolume;

    public VolumeGreaterThanSpecification(double minVolume) {
        this.minVolume = minVolume;
    }

    @Override
    protected boolean check(PyramidProperties props) {
        double volume = props.getVolume();
        boolean result = volume > minVolume;
        logger.debug("Volume check: value = {}, min required = {} -> {}", volume, minVolume, result);
        return result;
    }
}
