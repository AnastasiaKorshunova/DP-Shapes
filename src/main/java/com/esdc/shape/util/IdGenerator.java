package com.esdc.shape.util;

public class IdGenerator {
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 999999;
    private static long currentId = MIN_ID - 1;

    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static long getNextId() {
        if (currentId >= MAX_ID) {
            currentId = MIN_ID;
        }
        return ++currentId;
    }
}
