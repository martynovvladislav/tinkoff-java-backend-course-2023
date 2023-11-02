package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestsForTask4 {
    static Arguments[] negativeInputs() {
        return new Arguments[]{
            Arguments.of(-1),
            Arguments.of(4000)
        };
    }

    @ParameterizedTest
    @DisplayName("negative inputs test")
    @MethodSource("negativeInputs")
    void negativeInputTest(int number) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(number));
    }

    static Arguments[] positiveInputs() {
        return new Arguments[]{
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(2456, "MMCDLVI")
        };
    }

    @ParameterizedTest
    @DisplayName("positive inputs test")
    @MethodSource("positiveInputs")
    void positiveInputTest(int number, String expected) {
        Assertions.assertEquals(Task4.convertToRoman(number), expected);
    }
}
