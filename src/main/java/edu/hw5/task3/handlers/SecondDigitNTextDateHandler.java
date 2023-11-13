package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondDigitNTextDateHandler extends DateHandler {

    private static final Pattern PATTERN = Pattern.compile("^([1-9]\\d+) days ago$");

    public boolean canHandle(String date) {
        Matcher matcher = PATTERN.matcher(date);
        return matcher.matches();
    }

    public LocalDate handle(String date) {
        Matcher matcher = PATTERN.matcher(date);
        matcher.matches();
        int daysAgo = Integer.parseInt(matcher.group(1));
        return LocalDate.now().minusDays(daysAgo);
    }
}
