package edu.project3;

import edu.project3.logHandlers.LogRecord;
import edu.project3.logHandlers.StatisticsHandler;
import edu.project3.parsers.DateParser;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestsForStatisticsHandler {

    static Stream<Arguments> arguments() {
        List<LogRecord> logs = new ArrayList<>(List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                DateParser.parseDateTime("15/May/2015:08:05:32 +0000"),
                "GET",
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
                "urlgrabber/3.9.1 yum/3.4.3"
            )
        ));
        Map<String, Object> args = new HashMap<>(Map.of(
            "path",
            new ArrayList<Path>(List.of(Path.of("src/main/resources/project3/logs/2022/logs1.txt"))),
            "to",
            LocalDate.of(2015, 5, 28),
            "format",
            "someFormat"
        ));
        return Stream.of(
            Arguments.of(args, logs)
        );
    }

    @ParameterizedTest
    @DisplayName("get general information test")
    @MethodSource("arguments")
    void getGeneralInformationTest(Map<String, Object> args, List<LogRecord> logs) {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        Map<String, String> generalInformation = statisticsHandler.getGeneralInformation();
        Map<String, String> expected = new LinkedHashMap<>(Map.of(
            "Файл(-ы)",
            "logs1.txt",
            "Начальная дата",
            "-",
            "Конечная дата",
            "2015-05-28",
            "Количество запросов",
            "2",
            "Средний размер ответа",
            "0b"
        ));
        Assertions.assertEquals(expected, generalInformation);
    }

    @ParameterizedTest
    @DisplayName("get resources test")
    @MethodSource("arguments")
    void getResourcesTest(Map<String, Object> args, List<LogRecord> logs) {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        Map<String, String> resources = statisticsHandler.getResources();
        Map<String, String> expected = new LinkedHashMap<>(Map.of(
            "/downloads/product_1",
            "1",
            "/downloads/product_2",
            "1"
        ));
        Assertions.assertEquals(expected, resources);
    }

    @ParameterizedTest
    @DisplayName("get answer codes test")
    @MethodSource("arguments")
    void getAnswerCodesTest(Map<String, Object> args, List<LogRecord> logs) {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        Map<String, String> answerCodes = statisticsHandler.getAnswerCodes();
        Map<String, String> expected = new LinkedHashMap<>(Map.of(
            "304|Not Modified",
            "1",
            "404|Not Found",
            "1"
        ));
        Assertions.assertEquals(expected, answerCodes);
    }

    @ParameterizedTest
    @DisplayName("get http methods test")
    @MethodSource("arguments")
    void getHttpMethodsTest(Map<String, Object> args, List<LogRecord> logs) {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        Map<String, String> httpMethods = statisticsHandler.getHttpMethods();
        Map<String, String> expected = new LinkedHashMap<>(Map.of(
            "GET",
            "1",
            "POST",
            "1"
        ));
        Assertions.assertEquals(expected, httpMethods);
    }

    @ParameterizedTest
    @DisplayName("get top user agents test")
    @MethodSource("arguments")
    void getTopUserAgentsTest(Map<String, Object> args, List<LogRecord> logs) {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        Map<String, String> topUserAgents = statisticsHandler.getTopUserAgents();
        Map<String, String> expected = new LinkedHashMap<>(Map.of(
            "Debian",
            "1",
            "urlgrabber/3.9.1",
            "1"
        ));
        Assertions.assertEquals(expected, topUserAgents);
    }
}
