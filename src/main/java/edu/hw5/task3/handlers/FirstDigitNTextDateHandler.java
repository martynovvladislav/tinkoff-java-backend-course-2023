package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstDigitNTextDateHandler extends DateHandler {

    private static final Pattern PATTERN = Pattern.compile("^1 day ago$");

    public boolean canHandle(String date) {
        Matcher matcher = PATTERN.matcher(date);
        return matcher.matches();
    }

    public LocalDate handle(String date) {
        return LocalDate.now().minusDays(1);
    }
}
