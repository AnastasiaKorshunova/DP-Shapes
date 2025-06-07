package com.esdc.shape.validation;

import com.esdc.shape.entity.Point;
import com.esdc.shape.exception.InvalidPyramidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseValidator {

    private static final Logger logger = LoggerFactory.getLogger(BaseValidator.class);

    public static boolean validateCoplanarPoints(List<Point> points) {
        if (points == null || points.size() < 3) {
            logger.error("Invalid base: less than 3 points provided");
            throw new InvalidPyramidException("Base must contain at least three points.");
        }

        Point p0 = points.get(0);
        Point p1 = points.get(1);
        Point p2 = points.get(2);

        Point vector1 = subtract(p1, p0);
        Point vector2 = subtract(p2, p0);
        Point normal = crossProduct(vector1, vector2);

        for (int i = 3; i < points.size(); i++) {
            Point pi = points.get(i);
            Point vectorPi = subtract(pi, p0);
            double dot = dotProduct(normal, vectorPi);

            if (Math.abs(dot) > 1E-10) {
                logger.warn("Point {} is not in the base plane", pi);
                return false;
            }
        }

        logger.info("All base points are coplanar");
        return true;
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
