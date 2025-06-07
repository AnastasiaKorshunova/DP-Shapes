package com.esdc.shape.state;

import com.esdc.shape.entity.Pyramid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidPyramidState implements PyramidState {
    private static final Logger logger = LoggerFactory.getLogger(InvalidPyramidState.class);

    @Override
    public void handle(Pyramid pyramid) {
        logger.warn("Pyramid {} is invalid. No further processing.", pyramid.getPyramidId());
    }
}
