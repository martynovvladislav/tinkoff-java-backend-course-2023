package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestsForTask5 {
    static Stream<Arguments> numbersInput() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }

    @ParameterizedTest
    @DisplayName("check if number is valid test")
    @MethodSource("numbersInput")
    void isValidTest(String number, boolean isValid) {
        Assertions.assertEquals(Task5.isValidNumber(number), isValid);
    }
}
