package com.esdc.shape.validation;

import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.state.InvalidPyramidState;
import com.esdc.shape.state.PyramidState;
import com.esdc.shape.state.ValidPyramidState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PyramidValidator {
    private static final Logger logger = LoggerFactory.getLogger(PyramidValidator.class);

    public static void validateAndSetState(Pyramid pyramid) {
        Point apex = pyramid.getApex();
        List<Point> basePoints = pyramid.getBase();

        PyramidState state;

        if (basePoints == null || basePoints.size() < 3) {
            logger.warn("Base must contain at least 3 points.");
            state = new InvalidPyramidState();
        } else if (!ApexValidator.isApexNotInBasePlane(apex, basePoints)) {
            logger.warn("Apex is coplanar with the base.");
            state = new InvalidPyramidState();
        } else if (!BaseValidator.validateCoplanarPoints(basePoints)) {
            logger.warn("Base points are not coplanar.");
            state = new InvalidPyramidState();
        } else {
            logger.info("Pyramid {} is valid.", pyramid.getPyramidId());
            state = new ValidPyramidState();
        }

        pyramid.setState(state);
        state.handle(pyramid);
    }
}
