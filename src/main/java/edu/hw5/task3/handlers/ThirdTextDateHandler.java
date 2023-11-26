package edu.hw5.task3.handlers;

import java.time.LocalDate;

public class ThirdTextDateHandler extends DateHandler {
    public LocalDate handle(String date) {
        if (date.equals("yesterday")) {
            return LocalDate.now().minusDays(1);
        }
        return null;
    }
}
