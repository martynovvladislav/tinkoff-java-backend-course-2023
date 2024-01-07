package edu.project3.logHandlers;

import edu.project3.outputGenerators.AdocOutputGenerator;
import edu.project3.outputGenerators.MarkdownOutputGenerator;
import edu.project3.parsers.HttpStatusNameParser;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsHandler {

    private static final String PATH_OPTION = "path";
    private static final String FROM_OPTION = "from";
    private static final String TO_OPTION = "to";
    private static final String FORMAT_OPTION = "format";
    private static final int USER_AGENTS_LIMIT = 5;
    private Map<String, Object> arguments;
    private List<LogRecord> logs;

    //stats
    private Map<String, String> generalInformation;
    private Map<String, String> resources;
    private Map<String, String> answerCodes;
    private Map<String, String> httpMethods;
    private Map<String, String> topUserAgents;

    public StatisticsHandler(Map<String, Object> args, List<LogRecord> logs) {
        this.arguments = args;
        this.logs = logs;
    }

    public void handleStatistics() {
        generalInformation = getGeneralInformation();
        //System.out.println(generalInformation.entrySet());
        resources = getResources();
        //System.out.println(resources.entrySet());
        answerCodes = getAnswerCodes();
        //System.out.println(answerCodes.entrySet());
        httpMethods = getHttpMethods();
        //System.out.println(httpMethods);
        topUserAgents = getTopUserAgents();
        //System.out.println(topUserAgents);
        generateOutput();
    }

    public void generateOutput() {
        if (arguments.containsKey(FORMAT_OPTION)) {
            if (arguments.get(FORMAT_OPTION).equals("markdown")) {
               new MarkdownOutputGenerator().generate(
                   generalInformation,
                   resources,
                   answerCodes,
                   httpMethods,
                   topUserAgents
               );
            } else {
                new AdocOutputGenerator().generate(
                    generalInformation,
                    resources,
                    answerCodes,
                    httpMethods,
                    topUserAgents
                );
            }
        } else {
            new MarkdownOutputGenerator().generate(
                generalInformation,
                resources,
                answerCodes,
                httpMethods,
                topUserAgents
            );
        }
    }

    public Map<String, String> getGeneralInformation() {
        Map<String, String> info = new LinkedHashMap<>();
        String files;
        if (arguments.get(PATH_OPTION) instanceof List<?>) {
            files = ((List<Path>) arguments.get(PATH_OPTION)).stream()
                .map(path -> path.getFileName().toString()).collect(Collectors.joining(", "));
        } else {
            files = arguments.get(PATH_OPTION).toString();
        }
        info.put("Файл(-ы)", files);

        String startDate;
        if (arguments.containsKey(FROM_OPTION)) {
            startDate = arguments.get(FROM_OPTION).toString();
        } else {
            startDate = "-";
        }
        info.put("Начальная дата", startDate);

        String endDate;
        if (arguments.containsKey(TO_OPTION)) {
            endDate = arguments.get(TO_OPTION).toString();
        } else {
            endDate = "-";
        }
        info.put("Конечная дата", endDate);

        info.put("Количество запросов", String.valueOf(logs.size()));

        info.put("Средний размер ответа",
            String.valueOf(logs
                .stream()
                .mapToLong(LogRecord::bodyBytesSent)
                .sum() / logs.size()) + "b"
        );

        return info;
    }

    public Map<String, String> getResources() {
        Map<String, Integer> resAmount = new HashMap<>();
        logs.forEach(
            logRecord -> {
                if (resAmount.containsKey(logRecord.address())) {
                    resAmount.put(logRecord.address(), resAmount.get(logRecord.address()) + 1);
                } else {
                    resAmount.put(logRecord.address(), 1);
                }
            }
        );
        return resAmount.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue().toString(),
                (oldValue, newValue) -> oldValue, LinkedHashMap::new)
            );
    }

    public Map<String, String> getAnswerCodes() {
        Map<Integer, Integer> codesAmount = new HashMap<>();
        logs.forEach(
            logRecord -> {
                if (codesAmount.containsKey(logRecord.status())) {
                    codesAmount.put(logRecord.status(), codesAmount.get(logRecord.status()) + 1);
                } else {
                    codesAmount.put(logRecord.status(), 1);
                }
            }
        );
        return codesAmount.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(
                e -> e.getKey().toString() + "|" + HttpStatusNameParser.parseStatusName(String.valueOf(e.getKey())),
                e -> e.getValue().toString(),
                (oldValue, newValue) -> oldValue, LinkedHashMap::new)
            );
    }

    public Map<String, String> getHttpMethods() {
        Map<String, Integer> methodsAmount = new HashMap<>();
        logs.forEach(
            logRecord -> {
                if (methodsAmount.containsKey(logRecord.request())) {
                    methodsAmount.put(logRecord.request(), methodsAmount.get(logRecord.request()) + 1);
                } else {
                    methodsAmount.put(logRecord.request(), 1);
                }
            }
        );
        return methodsAmount.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue().toString(),
                (oldValue, newValue) -> oldValue, LinkedHashMap::new)
            );
    }

    public Map<String, String> getTopUserAgents() {
        Map<String, Integer> userAgentsAmount = new HashMap<>();
        logs.forEach(
            logRecord -> {
                String key = logRecord.userAgent().split(" ")[0];
                if (userAgentsAmount.containsKey(key)) {
                    userAgentsAmount.put(key, userAgentsAmount.get(key) + 1);
                } else {
                    userAgentsAmount.put(key, 1);
                }
            }
        );
        return userAgentsAmount.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(USER_AGENTS_LIMIT)
            .collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue().toString(),
                (oldValue, newValue) -> oldValue, LinkedHashMap::new)
            );
    }
}
