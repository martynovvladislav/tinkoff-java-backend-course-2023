package edu.project3.parsers;

import edu.project3.logHandlers.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private LogParser() {}

    private static final String REGEX =
        "^(\\S+) - (\\S+) \\[(\\S+ \\+0000)] \"(\\S+) (\\S+) (\\S+)\" (\\d+) (\\d+) \"(\\S+)\" \"(.*)\"$";

    @SuppressWarnings("checkstyle:MagicNumber")
    public static LogRecord parseLog(String log) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(log);
        matcher.matches();
        try {
            return new LogRecord(
                matcher.group(1),
                matcher.group(2),
                DateParser.parseDateTime(matcher.group(3)),
                matcher.group(4),
                matcher.group(5),
                matcher.group(6),
                Integer.parseInt(matcher.group(7)),
                Integer.parseInt(matcher.group(8)),
                matcher.group(9),
                matcher.group(10)
            );
        } catch (MatchException e) {
            throw new RuntimeException("Could not parse log in a not nginx format");
        }

    }
}
