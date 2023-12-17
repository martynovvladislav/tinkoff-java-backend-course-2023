package edu.hw7;

import edu.hw7.task2.FactorialCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask2 {
    @Test
    @DisplayName("factorial multithread calculations test")
    void factorialTest() {
        Assertions.assertEquals(FactorialCalculator.calculate(6), 720);
    }
}
