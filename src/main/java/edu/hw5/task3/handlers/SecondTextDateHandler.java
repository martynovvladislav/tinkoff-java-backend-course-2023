package edu.hw5.task3.handlers;

import java.time.LocalDate;

public class SecondTextDateHandler extends DateHandler {
    public boolean canHandle(String date) {
        return date.equals("today");
    }

    public LocalDate handle(String date) {
        return LocalDate.now();
    }
}
