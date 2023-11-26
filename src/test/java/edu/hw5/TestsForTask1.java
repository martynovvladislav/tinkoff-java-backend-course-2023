package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TestsForTask1 {
    @Test
    @DisplayName("one session test")
    void getOneSessionTimeTest() {
        Assertions.assertEquals(
            Task1.getAverageSessionTime(new ArrayList<String>(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50"))),
            "3ч 30м"
        );
    }

    @Test
    @DisplayName("few sessions test")
    void getAverageSessionTimeTest() {
        Assertions.assertEquals(
            Task1.getAverageSessionTime(
                new ArrayList<String>(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"))
            ), "3ч 40м"
        );
    }
}
