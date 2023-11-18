package edu.project3;

import edu.project3.parsers.HttpStatusNameParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestsForHttpStatusNameParser {

    static Stream<Arguments> statusList() {
        return Stream.of(
            Arguments.of("200", "OK"),
            Arguments.of("301", "Moved Permanently"),
            Arguments.of("404", "Not Found"),
            Arguments.of("502", "Bad Gateway")
        );
    }

    @ParameterizedTest
    @DisplayName("parse name of status using code test")
    @MethodSource("statusList")
    void parseStatusNameTest(String code, String expected) {
        Assertions.assertEquals(HttpStatusNameParser.parseStatusName(code), expected);
    }
}
