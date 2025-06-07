package com.esdc.shape.validation;

import com.esdc.shape.entity.Point;
import com.esdc.shape.exception.InvalidPyramidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApexValidator {

    private static final Logger logger = LoggerFactory.getLogger(ApexValidator.class);

    public static boolean isApexNotInBasePlane(Point apex, List<Point> basePoints) {
        if (basePoints == null || basePoints.size() < 3) {
            logger.error("Base must have at least 3 points. Provided: {}",
                    basePoints == null ? 0 : basePoints.size());
            throw new InvalidPyramidException("Base must contain at least three points to define a plane.");
        }

        Point p0 = basePoints.get(0);
        Point p1 = basePoints.get(1);
        Point p2 = basePoints.get(2);

        Point vector1 = subtract(p1, p0);
        Point vector2 = subtract(p2, p0);
        Point normal = crossProduct(vector1, vector2);
        Point vectorToApex = subtract(apex, p0);

        double dot = dotProduct(normal, vectorToApex);

        boolean isOutside = Math.abs(dot) > 1E-10;

        logger.debug("Apex {} is {}in the plane of base", apex, isOutside ? "not " : "");
        return isOutside;
    }

    private static Point subtract(Point a, Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    private static Point crossProduct(Point v1, Point v2) {
        return new Point(
                v1.getY() * v2.getZ() - v1.getZ() * v2.getY(),
                v1.getZ() * v2.getX() - v1.getX() * v2.getZ(),
                v1.getX() * v2.getY() - v1.getY() * v2.getX()
        );
    }

    private static double dotProduct(Point v1, Point v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }
}
