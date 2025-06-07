package com.esdc.shape.parser.impl;

import com.esdc.shape.entity.Point;
import com.esdc.shape.parser.PointParser;
import com.esdc.shape.validation.PointFormatValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PointParserImpl implements PointParser {
    private static final Logger logger = LoggerFactory.getLogger(PointParserImpl.class);

    @Override
    public List<Point> parsePoints(List<String> lines) {
        List<Point> points = new ArrayList<>();
        int lineNumber = 0;
        int skippedLines = 0;

        for (String line : lines) {
            lineNumber++;
            if (line == null || line.trim().isEmpty()) {
                logger.warn("Skipping empty or null line at {}", lineNumber);
                skippedLines++;
                continue;
            }

            if (!PointFormatValidator.isValid(line)) {
                logger.warn("Skipping invalid format on line {}: {}", lineNumber, line);
                skippedLines++;
                continue;
            }

            String[] tokens = line.trim().split(",", -1);
            if (tokens.length != 3) {
                logger.warn("Skipping line with invalid number of coordinates at line {}: {}", lineNumber, line);
                skippedLines++;
                continue;
            }

            try {
                double x = Double.parseDouble(tokens[0]);
                double y = Double.parseDouble(tokens[1]);
                double z = Double.parseDouble(tokens[2]);
                points.add(new Point(x, y, z));
            } catch (NumberFormatException e) {
                logger.warn("Skipping invalid number format on line {}: {}", lineNumber, line);
                skippedLines++;
            }
        }

        logger.info("Parsed {} valid points out of {} lines ({} skipped)", points.size(), lines.size(), skippedLines);
        return points;
    }
}
