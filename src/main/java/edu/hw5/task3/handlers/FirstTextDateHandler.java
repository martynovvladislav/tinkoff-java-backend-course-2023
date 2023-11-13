package edu.hw5.task3.handlers;

import java.time.LocalDate;

public class FirstTextDateHandler extends DateHandler {
    public boolean canHandle(String date) {
        return date.equals("tomorrow");
    }

    public LocalDate handle(String date) {
        return LocalDate.now().plusDays(1);
    }
}
