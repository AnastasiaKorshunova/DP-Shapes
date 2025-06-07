package com.esdc.shape.entity;

import com.esdc.shape.observer.impl.WarehouseObserver;
import com.esdc.shape.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PyramidTest {

    private Pyramid build(double side, double height) {
        Point apex = new Point(0, 0, height);
        List<Point> base = List.of(
                new Point(0, 0, 0),
                new Point(side, 0, 0),
                new Point(side / 2, side * Math.sqrt(3) / 2, 0)
        );
        Pyramid p = new Pyramid(apex, base);
        p.addObserver(new WarehouseObserver());
        p.setBase(base);            // вызовет notifyObservers → Warehouse обновится
        return p;
    }

    @BeforeEach
    void clearWarehouse() {
        Warehouse.getInstance().clearPropertiesMap();
    }

    @Test
    void geometry() {
        Pyramid p = build(1, 3);
        assertEquals(3.0,          p.getHeight(), 1e-6);
        assertEquals(0.4330127,    p.getArea(),   1e-5);
        assertEquals(0.4330127,    p.getVolume(), 1e-5); // 0.4330127 * 3 / 3
    }

    @Test
    void observerUpdatesWarehouse() {
        Pyramid p = build(1, 2);
        p.setApex(new Point(0, 0, 10));           // notifyObservers
        var props = Warehouse.getInstance().getProperties(p.getPyramidId());
        assertEquals(10.0, props.getHeight(), 1e-6);
    }

    @Test
    void equalsAndHashCode() {
        Pyramid a = build(1, 2);
        Pyramid b = build(1, 2);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}