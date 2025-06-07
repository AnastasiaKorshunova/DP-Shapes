package com.esdc.shape.parser;

import com.esdc.shape.entity.Point;

import java.util.List;

public interface PointParser {
    List<Point> parsePoints(List<String> lines);
}