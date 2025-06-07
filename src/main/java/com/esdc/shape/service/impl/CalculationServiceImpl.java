package com.esdc.shape.service.impl;

import com.esdc.shape.entity.Point;
import com.esdc.shape.service.CalculationService;

import java.util.List;

public class CalculationServiceImpl implements CalculationService {

    private static final CalculationServiceImpl INSTANCE = new CalculationServiceImpl();

    private CalculationServiceImpl() {}

    public static CalculationServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculateVolume(double area, Point apex) {
        return area * calculateHeight(apex) / 3;
    }

    @Override
    public double calculateHeight(Point apex) {
        return apex.getZ();
    }

    @Override
    public double calculateArea(List<Point> base) {
        return switch (base.size()) {
            case 3 -> calculateTriangleArea(base);
            case 4 -> calculateRectangleArea(base);
            default -> calculatePolygonArea(base);
        };
    }

    @Override
    public boolean checkBaseRegularity(List<Point> base) {
        if (base.size() < 3) return false;
        double firstSide = distanceBetween(base.get(0), base.get(1));
        for (int i = 1; i < base.size(); i++) {
            Point current = base.get(i);
            Point next = base.get((i + 1) % base.size());
            double side = distanceBetween(current, next);
            if (Math.abs(side - firstSide) > 1E-6) return false;
        }
        return true;
    }

    private double calculateTriangleArea(List<Point> points) {
        return 0.5 * Math.abs(
                points.get(0).getX() * (points.get(1).getY() - points.get(2).getY()) +
                        points.get(1).getX() * (points.get(2).getY() - points.get(0).getY()) +
                        points.get(2).getX() * (points.get(0).getY() - points.get(1).getY())
        );
    }

    private double calculateRectangleArea(List<Point> points) {
        double width = distanceBetween(points.get(0), points.get(1));
        double height = distanceBetween(points.get(1), points.get(2));
        return width * height;
    }

    private double calculatePolygonArea(List<Point> points) {
        double area = 0;
        for (int i = 0; i < points.size(); i++) {
            int j = (i + 1) % points.size();
            area += points.get(i).getX() * points.get(j).getY();
            area -= points.get(j).getX() * points.get(i).getY();
        }
        return 0.5 * Math.abs(area);
    }

    private double distanceBetween(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) +
                Math.pow(p2.getY() - p1.getY(), 2) +
                Math.pow(p2.getZ() - p1.getZ(), 2));
    }
}
