package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class FirstDigitNTextDateHandler extends DateHandler {

    private static final Pattern PATTERN = Pattern.compile("^1 day ago$");

    public LocalDate handle(String date) {
        if (PATTERN.matcher(date).matches()) {
            return LocalDate.now().minusDays(1);
        }
        return null;
    }
}
