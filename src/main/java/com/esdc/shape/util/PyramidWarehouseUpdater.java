package com.esdc.shape.util;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.service.impl.CalculationServiceImpl;
import com.esdc.shape.warehouse.PyramidProperties;
import com.esdc.shape.warehouse.Warehouse;

public class PyramidWarehouseUpdater {

    private PyramidWarehouseUpdater() {
    }

    public static void update(Pyramid pyramid) {
        double area = CalculationServiceImpl.getInstance().calculateArea(pyramid.getBase());
        double height = CalculationServiceImpl.getInstance().calculateHeight(pyramid.getApex());
        double volume = CalculationServiceImpl.getInstance().calculateVolume(area, pyramid.getApex());
        boolean isRegular = CalculationServiceImpl.getInstance().checkBaseRegularity(pyramid.getBase());

        Warehouse.getInstance().updateProperties(
                pyramid.getPyramidId(),
                new PyramidProperties(volume, area, height, isRegular, pyramid.getType())
        );
    }
}
