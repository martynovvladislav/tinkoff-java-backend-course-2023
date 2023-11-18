package edu.project3;

import edu.project3.parsers.DateParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestsForDateParser {
    @Test
    @DisplayName("parse date test")
    void parseLocalDateTest() {
        LocalDate localDate = LocalDate.of(2023, 12, 31);
        Assertions.assertEquals(localDate, DateParser.parseDate("31/Dec/2023"));
    }

    @Test
    @DisplayName("parse date time test")
    void parseLocalDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2011, 12, 3, 10, 15, 30);
        Assertions.assertEquals(localDateTime, DateParser.parseDateTime("2011-12-03T10:15:30"));
    }
}
