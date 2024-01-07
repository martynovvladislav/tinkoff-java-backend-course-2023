package edu.project3.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateParser {
    private DateParser() {
    }

    private static final List<DateTimeFormatter> PATTERNS = new ArrayList<>(List.of(
        DateTimeFormatter.BASIC_ISO_DATE,
        DateTimeFormatter.ISO_DATE,
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ISO_INSTANT,
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,
        DateTimeFormatter.ISO_LOCAL_TIME,
        DateTimeFormatter.ISO_OFFSET_DATE,
        DateTimeFormatter.ISO_OFFSET_DATE_TIME,
        DateTimeFormatter.ISO_OFFSET_TIME,
        DateTimeFormatter.ISO_ORDINAL_DATE,
        DateTimeFormatter.ISO_TIME,
        DateTimeFormatter.ISO_WEEK_DATE,
        DateTimeFormatter.ISO_ZONED_DATE_TIME,
        DateTimeFormatter.RFC_1123_DATE_TIME,
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH),
        DateTimeFormatter.ofPattern("dd/MMM/yyyy").withLocale(Locale.ENGLISH)
    ));

    public static LocalDateTime parseDateTime(String date) {
        LocalDateTime dateTime = null;
        for (DateTimeFormatter formatter : PATTERNS) {
            try {
                dateTime = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }
        return dateTime;
    }

    public static LocalDate parseDate(String date) {
        LocalDate dateTime = null;
        for (DateTimeFormatter formatter : PATTERNS) {
            try {
                dateTime = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }
        return dateTime;
    }
}
