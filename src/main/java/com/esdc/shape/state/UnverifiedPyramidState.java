package com.esdc.shape.state;

import com.esdc.shape.entity.Pyramid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnverifiedPyramidState implements PyramidState {
    private static final Logger logger = LoggerFactory.getLogger(UnverifiedPyramidState.class);

    @Override
    public void handle(Pyramid pyramid) {
        logger.info("Pyramid {} is in unverified state. Awaiting validation.", pyramid.getPyramidId());
    }
}