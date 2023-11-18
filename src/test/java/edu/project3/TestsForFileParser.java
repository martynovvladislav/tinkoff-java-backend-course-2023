package edu.project3;

import edu.project3.logHandlers.LogRecord;
import edu.project3.parsers.DateParser;
import edu.project3.parsers.FileParser;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestsForFileParser {

    static Stream<Arguments> fileRecords() {
        List<LogRecord> logs = new ArrayList<>(List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                DateParser.parseDateTime("15/May/2015:08:05:32 +0000"),
                "POST",
                "/downloads/product_1",
                "HTTP/1.1",
                304,
                0,
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new LogRecord(
                "93.180.71.3",
                "-",
                DateParser.parseDateTime("29/May/2015:08:10:10 +0000"),
                "POST",
                "/downloads/product_2",
                "HTTP/1.1",
                404,
                0,
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        ));
        return Stream.of(
            Arguments.of(logs)
        );
    }

    @ParameterizedTest
    @DisplayName("file parse test")
    @MethodSource("fileRecords")
    void parseFileTest(List<LogRecord> logs) {
        Assertions.assertEquals(logs, FileParser.parseFile(Path.of("src/main/resources/project3/logs/2022/logs1.txt")));
    }

    @Test
    @DisplayName("negative file input (files don't exist)")
    void parseNonExistingFilesTest() {
        Assertions.assertThrows(RuntimeException.class, () -> FileParser.parseFile(Path.of("12345")));
    }
}
