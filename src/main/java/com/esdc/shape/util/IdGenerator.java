package com.esdc.shape.util;

public class IdGenerator {
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 999999;
    private static long currentId = MIN_ID - 1;


    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method to generate the next ID without synchronization.
     * @return the next ID, or wraps around to MIN_ID if the max ID is reached.
     */
    public static long getNextId() {
        if (currentId >= MAX_ID) {
            currentId = MIN_ID;  // Wrap around to MIN_ID
            // Alternatively, you can throw an exception if you don't want wrapping:
            // throw new IllegalStateException("Maximum ID limit reached.");
        }
        return ++currentId;
    }
}
