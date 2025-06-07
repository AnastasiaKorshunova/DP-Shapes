package com.esdc.shape.factory.impl;

import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.exception.InvalidPyramidException;
import com.esdc.shape.factory.PyramidFactory;
import com.esdc.shape.observer.impl.WarehouseObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PyramidFactoryImpl implements PyramidFactory {
    private static final Logger logger = LoggerFactory.getLogger(PyramidFactoryImpl.class);

    @Override
    public Pyramid createPyramid(Point apex, List<Point> base) {
        if (base == null || base.size() < 3) {
            throw new InvalidPyramidException("Pyramid base must have at least 3 points");
        }

        logger.debug("Creating pyramid with apex: {} and base: {}", apex, base);

        Pyramid pyramid = new Pyramid(apex, base);
        pyramid.addObserver(new WarehouseObserver());

        logger.info("Pyramid created with ID: {}", pyramid.getPyramidId());
        return pyramid;
    }
}
