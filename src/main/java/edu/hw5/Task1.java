package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {
    private Task1() {}

    private static final int MINUTES_IN_HOUR = 60;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    public static String getAverageSessionTime(List<String> sessionDurations) {
        var duration = sessionDurations
            .stream()
            .map(sessionDuration -> sessionDuration.split(" - "))
            .map(sessionDuration -> Duration.between(
                LocalDateTime.parse(sessionDuration[0], FORMATTER), LocalDateTime.parse(sessionDuration[1], FORMATTER))
            ).reduce(Duration.ZERO, Duration::plus)
            .dividedBy(sessionDurations.size());
        return duration.toMinutes() / MINUTES_IN_HOUR + "ч " + duration.toMinutes() % MINUTES_IN_HOUR + "м";
    }
}
