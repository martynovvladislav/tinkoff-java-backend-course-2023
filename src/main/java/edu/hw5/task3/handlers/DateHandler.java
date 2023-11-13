package edu.hw5.task3.handlers;

import java.time.LocalDate;

public abstract class DateHandler {
    public abstract boolean canHandle(String date);

    public abstract LocalDate handle(String date);
}
