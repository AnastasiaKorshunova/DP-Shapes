package com.esdc.shape.comparator;

import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.entity.PyramidType;
import com.esdc.shape.observer.impl.WarehouseObserver;
import com.esdc.shape.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

abstract class BaseComparatorTest {

    @BeforeEach
    void clearWarehouse() {
        Warehouse.getInstance().clearPropertiesMap();
    }

    Pyramid build(double side, double apexZ) {
        Point apex = new Point(0, 0, apexZ);
        List<Point> base = List.of(
                new Point(0, 0, 0),
                new Point(side, 0, 0),
                new Point(side / 2, side * Math.sqrt(3) / 2, 0)
        );
        Pyramid p = new Pyramid(apex, base);
        p.setType(PyramidType.TRIANGLE);
        p.addObserver(new WarehouseObserver());
        p.setBase(base);
        return p;
    }
}
