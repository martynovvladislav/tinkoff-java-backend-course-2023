package edu.hw5.task3.handlers;

import java.time.LocalDate;

public class ThirdTextDateHandler extends DateHandler {
    public boolean canHandle(String date) {
        return date.equals("yesterday");
    }

    public LocalDate handle(String date) {
        return LocalDate.now().minusDays(1);
    }
}
