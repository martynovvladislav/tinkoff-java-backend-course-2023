package edu.project3.parsers;

import edu.project3.logHandlers.LogRecord;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private FileParser() {
    }

    public static List<LogRecord> parseFile(Path path) {
        List<LogRecord> logRecords = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                logRecords.add(LogParser.parseLog(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("IO Exception in File Parser!");
        }
        return logRecords;
    }
}
