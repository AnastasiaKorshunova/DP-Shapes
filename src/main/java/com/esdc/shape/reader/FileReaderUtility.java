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

    /**
     * Reads all lines from a text file.
     * @param filePath Path to the file.
     * @return A list of raw strings representing lines from the file.
     */
    public static List<String> readLines(String filePath) {
        Path path = Path.of(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            logger.info("Successfully read {} lines from {}", lines.size(), filePath);
            return lines;
        } catch (IOException e) {
            logger.error("Failed to read file at {}: {}", filePath, e.getMessage());
            return Collections.emptyList(); // или throw, если нужно прерывать
        }
    }
}
