package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static final int THIRTEEN = 13;

    public static List<LocalDate> getAllFridays13(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 1);
        while (date.getYear() == year) {
            if (date.getDayOfMonth() == THIRTEEN && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return fridays13;
    }

    public static LocalDate getNextFriday13(LocalDate date) {
        LocalDate tempDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (!(tempDate.getDayOfMonth() == THIRTEEN)) {
            tempDate = tempDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return tempDate;
    }
}
