package com.esdc.shape.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Long, PyramidProperties> propertiesMap = new HashMap<>();

    private Warehouse() {}

    public static synchronized Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void updateProperties(Long pyramidId, PyramidProperties properties) {
        propertiesMap.put(pyramidId, properties);
    }

    public PyramidProperties getProperties(Long pyramidId) {
        return propertiesMap.get(pyramidId);
    }

    public boolean contains(long id) {
        return propertiesMap.containsKey(id);
    }

    public Map<Long, PyramidProperties> getAllProperties() {
        return new HashMap<>(propertiesMap);
    }

    public void clearPropertiesMap() {
        propertiesMap.clear();
    }
}

