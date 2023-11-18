package edu.project3;

import edu.project3.logHandlers.LogRecord;
import edu.project3.logHandlers.StatisticsHandler;
import edu.project3.outputGenerators.MarkdownOutputGenerator;
import edu.project3.parsers.DateParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TestsForOutputGenerators {
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

    static String readFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @ParameterizedTest
    @DisplayName("test for markdown output")
    @MethodSource("arguments")
    void generateMarkdownTest(Map<String, Object> args, List<LogRecord> logs) throws IOException {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        statisticsHandler.handleStatistics();
        MarkdownOutputGenerator outputGenerator = new MarkdownOutputGenerator();
        outputGenerator.generate(
            statisticsHandler.getGeneralInformation(),
            statisticsHandler.getResources(),
            statisticsHandler.getAnswerCodes(),
            statisticsHandler.getHttpMethods(),
            statisticsHandler.getTopUserAgents()
        );
        String expected = "#### Общая информация\n" +
            "\n" +
            "| Метрика | Значение |\n" +
            "|:-:|-:|\n" +
            "| Файл(-ы) | logs1.txt |\n" +
            "| Начальная дата | - |\n" +
            "| Конечная дата | 2015-05-28 |\n" +
            "| Количество запросов | 2 |\n" +
            "| Средний размер ответа | 0b |\n" +
            "\n" +
            "#### Запрашиваемые ресурсы\n" +
            "\n" +
            "| Ресурс | Количество |\n" +
            "|:-:|-:|\n" +
            "| /downloads/product_1 | 1 |\n" +
            "| /downloads/product_2 | 1 |\n" +
            "\n" +
            "#### Коды ответа\n" +
            "\n" +
            "| Код | Имя | Количество |\n" +
            "|:-:|-:|-:|\n" +
            "| 304|Not Modified | 1 |\n" +
            "| 404|Not Found | 1 |\n" +
            "\n" +
            "#### HTTP методы\n" +
            "\n" +
            "| Метод | Количество |\n" +
            "|:-:|-:|\n" +
            "| POST | 1 |\n" +
            "| GET | 1 |\n" +
            "\n" +
            "#### HTTP агенты (топ 5)\n" +
            "\n" +
            "| Агент | Количество |\n" +
            "|:-:|-:|\n" +
            "| Debian | 1 |\n" +
            "| urlgrabber/3.9.1 | 1 |\n";
        String actual = readFile("src/main/java/edu/project3/output/output.md");
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("test for adoc output generator")
    @MethodSource("arguments")
    void generateAdocTest(Map<String, Object> args, List<LogRecord> logs) throws IOException {
        StatisticsHandler statisticsHandler = new StatisticsHandler(args, logs);
        statisticsHandler.handleStatistics();
        MarkdownOutputGenerator outputGenerator = new MarkdownOutputGenerator();
        outputGenerator.generate(
            statisticsHandler.getGeneralInformation(),
            statisticsHandler.getResources(),
            statisticsHandler.getAnswerCodes(),
            statisticsHandler.getHttpMethods(),
            statisticsHandler.getTopUserAgents()
        );
        String expected = "==== Общая информация\n" +
            "\n" +
            "[options=\"header\"]\n" +
            "|=====\n" +
            "| Метрика | Значение\n" +
            "| Файл(-ы) | logs1.txt\n" +
            "| Начальная дата | -\n" +
            "| Конечная дата | 2015-05-28\n" +
            "| Количество запросов | 2\n" +
            "| Средний размер ответа | 0b\n" +
            "|=====\n" +
            "\n" +
            "==== Запрашиваемые ресурсы\n" +
            "\n" +
            "[options=\"header\"]\n" +
            "|=====\n" +
            "| Ресурс | Количество\n" +
            "| /downloads/product_1 | 1\n" +
            "| /downloads/product_2 | 1\n" +
            "|=====\n" +
            "\n" +
            "==== Коды ответа\n" +
            "\n" +
            "[options=\"header\"]\n" +
            "|=====\n" +
            "| Код | Имя | Количество\n" +
            "| 304|Not Modified | 1\n" +
            "| 404|Not Found | 1\n" +
            "|=====\n" +
            "\n" +
            "==== HTTP методы\n" +
            "\n" +
            "[options=\"header\"]\n" +
            "|=====\n" +
            "| Метод | Количество\n" +
            "| POST | 1\n" +
            "| GET | 1\n" +
            "|=====\n" +
            "\n" +
            "==== HTTP агенты (топ 5)\n" +
            "\n" +
            "[options=\"header\"]\n" +
            "|=====\n" +
            "| Агент | Количество\n" +
            "| Debian | 1\n" +
            "| urlgrabber/3.9.1 | 1\n" +
            "|=====\n";
        String actual = readFile("src/main/java/edu/project3/output/output.adoc");
        Assertions.assertEquals(expected, actual);
    }
}
