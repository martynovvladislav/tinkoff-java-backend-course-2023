package edu.project3.logHandlers;

import edu.project3.parsers.FileParser;
import edu.project3.parsers.HttpParser;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogsCollector {
    private static final String PATH_OPTION = "path";
    private static final String FROM_OPTION = "from";
    private static final String TO_OPTION = "to";
    private List<LogRecord> logs = new ArrayList<>();
    private HttpParser httpParser;

    public LogsCollector() {
        this.httpParser = new HttpParser();
    }

    public LogsCollector(HttpParser parser) {
        this.httpParser = parser;
    }

    public void collectLogs(Map<String, Object> arguments) {
        if (arguments.get(PATH_OPTION) instanceof List<?>) {
            List<Path> paths = (List<Path>) arguments.get(PATH_OPTION);
            paths.forEach(path -> logs.addAll(FileParser.parseFile(path)));
        } else {
            String path = (String) arguments.get(PATH_OPTION);
            logs.addAll(httpParser.parseHttp(path));
        }

        if (arguments.containsKey(FROM_OPTION)) {
            LocalDateTime from;
            if (arguments.get(FROM_OPTION) instanceof LocalDate) {
                from = ((LocalDate) arguments.get(FROM_OPTION)).atStartOfDay();
            } else {
                from = (LocalDateTime) arguments.get(FROM_OPTION);
            }
            logs = logs.stream().filter(logRecord -> logRecord.timeLocal().isAfter(from)).toList();
        }

        if (arguments.containsKey(TO_OPTION)) {
            LocalDateTime to;
            if (arguments.get(TO_OPTION) instanceof LocalDate) {
                to = ((LocalDate) arguments.get(TO_OPTION)).atStartOfDay();
            } else {
                to = (LocalDateTime) arguments.get(TO_OPTION);
            }
            logs = logs.stream().filter(logRecord -> logRecord.timeLocal().isBefore(to)).toList();
        }
        if (logs.isEmpty()) {
            throw new RuntimeException("There are no logs to make statistics list!");
        }
        //logs.forEach(System.out::println);
    }

    public List<LogRecord> getLogs() {
        return logs;
    }
}
