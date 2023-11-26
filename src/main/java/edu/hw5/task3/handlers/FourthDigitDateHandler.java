package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FourthDigitDateHandler extends DateHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yy");

    public LocalDate handle(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
