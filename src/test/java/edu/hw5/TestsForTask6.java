package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestsForTask6 {
    static Stream<Arguments> subsequencesInput() {
        return Stream.of(
            Arguments.of("abc", "achfdbaabgabcaabg", true),
            Arguments.of("abc", "aebece", true),
            Arguments.of("abc", "cba", false)
        );
    }

    @ParameterizedTest
    @DisplayName("check if string is subsequence of another string test")
    @MethodSource("subsequencesInput")
    void isSubsequenceTest(String subsequence, String string, boolean isSubsequence) {
        Assertions.assertEquals(Task6.isSubsequence(subsequence, string), isSubsequence);
    }
}
