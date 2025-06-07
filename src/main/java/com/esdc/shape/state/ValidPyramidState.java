package com.esdc.shape.state;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.util.PyramidWarehouseUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidPyramidState implements PyramidState {
    private static final Logger logger = LoggerFactory.getLogger(ValidPyramidState.class);

    @Override
    public void handle(Pyramid pyramid) {
        logger.info("Pyramid {} is valid. Proceeding to update warehouse.", pyramid.getPyramidId());
        PyramidWarehouseUpdater.update(pyramid);
        logger.info("Warehouse updated for Pyramid {}", pyramid.getPyramidId());
    }
}
