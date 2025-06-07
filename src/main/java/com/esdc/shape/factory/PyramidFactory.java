package com.esdc.shape.factory;

import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;

import java.util.List;

public interface PyramidFactory {
    Pyramid createPyramid(Point apex, List<Point> base);
}
