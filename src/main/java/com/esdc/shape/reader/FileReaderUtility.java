package com.esdc.shape.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileReaderUtility {

    private static final Logger logger = LoggerFactory.getLogger(FileReaderUtility.class);
    private static List<String> strings;

    public static List<String> readLines(String filePath) {
        Path path = Path.of(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            logger.info("Successfully read {} lines from {}", lines.size(), filePath);
            return lines;
        } catch (IOException e) {
            logger.error("Failed to read file at {}: {}", filePath, e.getMessage());
            return Collections.emptyList();
        }
    }
}
