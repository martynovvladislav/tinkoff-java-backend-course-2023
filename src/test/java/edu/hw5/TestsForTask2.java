package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask2 {
    @Test
    @DisplayName("get all Fridays 13 in a year")
    void getAllFridays13Test() {
        Assertions.assertEquals(
            Task2.getAllFridays13(1925),
            new ArrayList<LocalDate>(List.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13)
            ))
        );
        Assertions.assertEquals(
            Task2.getAllFridays13(2024),
            new ArrayList<LocalDate>(List.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            ))
        );
    }

    @Test
    @DisplayName("get next Friday 13 test")
    void getNextFriday13Test() {
        Assertions.assertEquals(
            Task2.getNextFriday13(LocalDate.of(1925, 2, 13)),
            LocalDate.of(1925, 3, 13)
        );
        Assertions.assertEquals(
            Task2.getNextFriday13(LocalDate.of(2024, 9, 13)),
            LocalDate.of(2024, 12, 13)
        );
    }
}
