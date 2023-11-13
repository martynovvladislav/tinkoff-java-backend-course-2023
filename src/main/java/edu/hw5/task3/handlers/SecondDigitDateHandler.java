package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SecondDigitDateHandler extends DateHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public boolean canHandle(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDate handle(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
