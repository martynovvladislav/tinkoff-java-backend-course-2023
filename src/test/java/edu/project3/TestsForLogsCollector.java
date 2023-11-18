package edu.project3;

import edu.project3.logHandlers.LogRecord;
import edu.project3.logHandlers.LogsCollector;
import edu.project3.parsers.DateParser;
import edu.project3.parsers.HttpParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.mockito.ArgumentMatchers.any;

public class TestsForLogsCollector {

    static Stream<Arguments> arguments() {
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
    @DisplayName("test collecting logs from file")
    @MethodSource("arguments")
    void collectFileLogsTest(List<LogRecord> logs) {
        Map<String, Object> args = new HashMap<>(Map.of(
            "path",
            new ArrayList<Path>(List.of(Path.of("src/main/resources/project3/logs/2022/logs1.txt"))),
            "format",
            "someFormat"
        ));
        LogsCollector collector = new LogsCollector();
        collector.collectLogs(args);
        Assertions.assertEquals(logs, collector.getLogs());
    }

    @ParameterizedTest
    @DisplayName("test collecting logs from http")
    @MethodSource("arguments")
    void collectHttpLogsTest(List<LogRecord> logs) {
        Map<String, Object> args = new HashMap<>(Map.of(
            "path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "format",
            "someFormat"
        ));
        HttpParser httpParser = Mockito.mock(HttpParser.class);
        Mockito.when(httpParser.parseHttp(any())).thenReturn(logs);
        LogsCollector collector = new LogsCollector(httpParser);
        collector.collectLogs(args);
        Assertions.assertEquals(logs, collector.getLogs());
    }

    @ParameterizedTest
    @DisplayName("test collecting logs from file and filter them by from date input")
    @MethodSource("arguments")
    void collectLogsWithFromDateTest(List<LogRecord> logs) {
        Map<String, Object> args = new HashMap<>(Map.of(
            "path",
            new ArrayList<Path>(List.of(Path.of("src/main/resources/project3/logs/2022/logs1.txt"))),
            "from",
            LocalDate.of(2015, 5, 28),
            "format",
            "someFormat"
        ));
        LogsCollector collector = new LogsCollector();
        collector.collectLogs(args);
        logs.remove(0);
        Assertions.assertEquals(
            logs,
            collector.getLogs()
        );
    }

    @ParameterizedTest
    @DisplayName("test collecting logs from file and filter them by to date input")
    @MethodSource("arguments")
    void collectLogsWithToDateTest(List<LogRecord> logs) {
        Map<String, Object> args = new HashMap<>(Map.of(
            "path",
            new ArrayList<Path>(List.of(Path.of("src/main/resources/project3/logs/2022/logs1.txt"))),
            "to",
            LocalDate.of(2015, 5, 28),
            "format",
            "someFormat"
        ));
        LogsCollector collector = new LogsCollector();
        collector.collectLogs(args);
        logs.remove(logs.size() - 1);
        Assertions.assertEquals(
            logs,
            collector.getLogs()
        );
    }
}
