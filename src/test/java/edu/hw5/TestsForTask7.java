package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask7 {
    @Test
    @DisplayName("test if string contains more than 2 symbols and third is '0'")
    void regex1Test() {
        Assertions.assertTrue(Task7.firstRegex("100"));
        Assertions.assertFalse(Task7.firstRegex("101"));
        Assertions.assertFalse(Task7.firstRegex("10"));
    }

    @Test
    @DisplayName("test if string is started and ended with the same character")
    void regex2Test() {
        Assertions.assertTrue(Task7.secondRegex("10001"));
        Assertions.assertFalse(Task7.secondRegex("100"));
    }

    @Test
    @DisplayName("test if string length is more than 0 and less than 4")
    void regex3Test() {
        Assertions.assertTrue(Task7.thirdRegex("100"));
        Assertions.assertFalse(Task7.thirdRegex("1000"));
    }

    @Test
    @DisplayName("test if {0, 1} alphabet in string")
    void alphabetTest() {
        Assertions.assertFalse(Task7.firstRegex("abcde"));
        Assertions.assertFalse(Task7.secondRegex("abcde"));
        Assertions.assertFalse(Task7.thirdRegex("abcde"));
    }
}
