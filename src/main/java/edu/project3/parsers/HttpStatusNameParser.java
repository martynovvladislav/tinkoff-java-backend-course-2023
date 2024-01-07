package edu.project3.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HttpStatusNameParser {
    private HttpStatusNameParser() {}

    private static final String FILE_PATH = "src/main/resources/project3/notlogs/httpcodes.txt";

    public static String parseStatusName(String code) {
        try {
            Map<String, String> statusMap = readStatusCodesFromFile(FILE_PATH);
            return statusMap.get(code);
        } catch (Exception e) {
            return "";
        }
    }

    private static Map<String, String> readStatusCodesFromFile(String filePath) {
        Map<String, String> statusMap = new HashMap<>();

        try {
            Files.lines(Paths.get(filePath))
                .forEach(line -> {
                    String[] parts = line.split(" ", 2);
                        String statusCode = parts[0];
                        String statusName = parts[1];
                        statusMap.put(statusCode, statusName);
                });
        } catch (IOException e) {
            throw new RuntimeException("IO Exception in Http Status Parser");
        }

        return statusMap;
    }
}
