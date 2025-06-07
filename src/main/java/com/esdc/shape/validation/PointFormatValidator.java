package com.esdc.shape.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointFormatValidator {

    private static final Logger logger = LoggerFactory.getLogger(PointFormatValidator.class);

    public static boolean isValid(String data) {
        if (data == null || data.isBlank()) {
            logger.warn("Empty or null input string for point validation");
            return false;
        }

        String[] coords = data.trim().split(",");
        if (coords.length != 3) {
            logger.warn("Invalid point format: expected 3 coordinates, got {}: {}", coords.length, data);
            return false;
        }

        for (String coord : coords) {
            try {
                Double.parseDouble(coord.trim());
            } catch (NumberFormatException e) {
                logger.warn("Invalid coordinate value (not a double): {}", coord);
                return false;
            }
        }

        return true;
    }
}
