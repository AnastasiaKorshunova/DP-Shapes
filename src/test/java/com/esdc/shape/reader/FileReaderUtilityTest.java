package com.esdc.shape.reader;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderUtilityTest {

    private List<String> read(String resource) {
        URL url = getClass().getClassLoader().getResource(resource);
        assertNotNull(url, "Resource not found: " + resource);
        return FileReaderUtility.readLines(Path.of(url.getPath()).toString());
    }

    @Test
    void validFile_readsExactly12Lines() {
        List<String> lines = read("valid_points.txt");
        assertEquals(12, lines.size());
    }

    @Test
    void invalidFile_readsAllLinesEvenIfBad() {
        List<String> lines = read("invalid_points.txt");
        // если в invalid_points.txt действительно 4 строк—проверь число
        assertEquals(4, lines.size());
    }

    @Test
    void mixedFile_readsFullContent() {
        List<String> lines = read("mixed_points.txt");
        // mixed_points.txt содержит 35 валидных + комментарии → подставь фактическое
        assertTrue(lines.size() >= 35);
    }

    @Test
    void nonexistentFile_returnsEmptyList() {
        List<String> lines = FileReaderUtility.readLines("no_such_file.txt");
        assertTrue(lines.isEmpty());
    }
}
