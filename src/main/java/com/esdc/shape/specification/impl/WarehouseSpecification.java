package com.esdc.shape.specification.impl;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.specification.Specification;
import com.esdc.shape.warehouse.PyramidProperties;
import com.esdc.shape.warehouse.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WarehouseSpecification implements Specification<Pyramid> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean isSatisfiedBy(Pyramid pyramid) {
        PyramidProperties props = Warehouse.getInstance().getProperties(pyramid.getPyramidId());
        if (props == null) {
            logger.warn("No properties found in warehouse for pyramid ID {}", pyramid.getPyramidId());
            return false;
        }
        return check(props);
    }

    protected abstract boolean check(PyramidProperties props);
}
