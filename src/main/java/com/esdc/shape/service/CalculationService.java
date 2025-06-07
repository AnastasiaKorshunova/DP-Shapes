package com.esdc.shape.service;

import com.esdc.shape.entity.Point;

import java.util.List;

public interface CalculationService {
    double calculateVolume(double area, Point apex);
    double calculateHeight(Point apex);
    double calculateArea(List<Point> base);
    boolean checkBaseRegularity(List<Point> base);
}
