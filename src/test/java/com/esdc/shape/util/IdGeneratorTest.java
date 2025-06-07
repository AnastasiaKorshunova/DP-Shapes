package com.esdc.shape.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class IdGeneratorTest {

    private long minId;
    private long maxId;
    private Field currentIdField;

    @BeforeEach
    void resetState() throws Exception {
        Field minField = IdGenerator.class.getDeclaredField("MIN_ID");
        minField.setAccessible(true);
        minId = minField.getLong(null);

        Field maxField = IdGenerator.class.getDeclaredField("MAX_ID");
        maxField.setAccessible(true);
        maxId = maxField.getLong(null);

        currentIdField = IdGenerator.class.getDeclaredField("currentId");
        currentIdField.setAccessible(true);
        currentIdField.setLong(null, minId - 1);
    }

    @Test
    void firstCallReturnsMinId() {
        long id = IdGenerator.getNextId();
        assertEquals(minId, id, "First generated ID should equal MIN_ID");
    }

    @Test
    void subsequentCallIncrementsId() {
        long first = IdGenerator.getNextId();
        long second = IdGenerator.getNextId();
        assertEquals(first + 1, second, "IDs must increase by one");
    }

    @Test
    void neverReturnsValueAboveMaxId_evenAfterWrap() throws Exception {
        currentIdField.setLong(null, maxId - 1);

        long lastValid = IdGenerator.getNextId();
        long wrapped   = IdGenerator.getNextId();

        assertEquals(maxId, lastValid,
                "Last ID before wrap should equal MAX_ID");
        assertTrue(wrapped >= minId && wrapped <= maxId,
                "Wrapped ID should stay within [MIN_ID, MAX_ID]");
    }
}
