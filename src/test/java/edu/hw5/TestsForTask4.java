package edu.hw5;

import edu.hw5.task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class TestsForTask4 {
    static Stream<Arguments> passwords() {
        return Stream.of(
            Arguments.of("!abcde", true),
            Arguments.of("a@bcde", true),
            Arguments.of("ab#cde", true),
            Arguments.of("abc$de", true),
            Arguments.of("abcd%e", true),
            Arguments.of("abcde^", true),
            Arguments.of("abcdef&", true),
            Arguments.of("passw*rd", true),
            Arguments.of("passwor|d", true),
            Arguments.of("!@#$%^&*|", true),
            Arguments.of("defaultPassword", false)
        );
    }

    @ParameterizedTest
    @DisplayName("check if passwords are valid test")
    @MethodSource("passwords")
    void areValidPasswordsTest(String inputPassword, boolean isValid) {
        Assertions.assertEquals(Task4.isValidPassword(inputPassword), isValid);
    }
}
