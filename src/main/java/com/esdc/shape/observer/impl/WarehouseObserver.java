package com.esdc.shape.observer.impl;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.observer.PyramidObservable;
import com.esdc.shape.observer.PyramidObserver;
import com.esdc.shape.util.PyramidWarehouseUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarehouseObserver implements PyramidObserver {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseObserver.class);

    @Override
    public void update(PyramidObservable observable, Object arg) {
        if (observable instanceof Pyramid pyramid) {
            logger.info("Observer triggered update for Pyramid {}", pyramid.getPyramidId());
            PyramidWarehouseUpdater.update(pyramid);
        } else {
            logger.warn("Received update from unknown observable: {}", observable.getClass().getSimpleName());
        }
    }
}
