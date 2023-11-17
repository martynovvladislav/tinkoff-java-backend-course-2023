package edu.hw5.task3.handlers;

import java.time.LocalDate;

public class SecondTextDateHandler extends DateHandler {

    public LocalDate handle(String date) {
        if (date.equals("today")) {
            return LocalDate.now();
        }
        return null;
    }
}
