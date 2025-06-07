package com.esdc.shape.warehouse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class WarehouseTest {

    @AfterEach
    void clear() {
        Warehouse.getInstance().clearPropertiesMap();
    }

    @Test
    void getInstanceReturnsSingleton() {
        Warehouse w1 = Warehouse.getInstance();
        Warehouse w2 = Warehouse.getInstance();

        assertSame(w1, w2);
    }

    @Test
    void updateAndRetrieveProperties() {
        Warehouse warehouse = Warehouse.getInstance();
        PyramidProperties props = new PyramidProperties(1.0, 2.0, 3.0, false, null);

        warehouse.updateProperties(1L, props);

        assertEquals(props, warehouse.getProperties(1L));
    }

    @Test
    void clearPropertiesMapEmptiesStorage() {
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.updateProperties(1L, new PyramidProperties(1.0, 2.0, 3.0, false, null));

        warehouse.clearPropertiesMap();

        assertNull(warehouse.getProperties(1L));
    }

}
