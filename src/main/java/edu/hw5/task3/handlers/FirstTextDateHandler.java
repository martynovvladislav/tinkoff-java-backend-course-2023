package edu.hw5.task3.handlers;

import java.time.LocalDate;

public class FirstTextDateHandler extends DateHandler {

    public LocalDate handle(String date) {
        if (date.equals("tomorrow")) {
            return LocalDate.now().plusDays(1);
        }
        return null;
    }
}
