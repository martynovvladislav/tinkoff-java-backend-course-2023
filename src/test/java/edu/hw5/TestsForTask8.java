package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask8 {
    @Test
    @DisplayName("check is string length is odd test")
    void regex1Test() {
        Assertions.assertTrue(Task8.firstRegex("101"));
        Assertions.assertFalse(Task8.firstRegex("1000"));
    }

    @Test
    @DisplayName("check if string starts with 0 and has an odd length OR starts with 1 and has an even length test")
    void regex2Test() {
        Assertions.assertTrue(Task8.secondRegex("010"));
        Assertions.assertFalse(Task8.secondRegex("110"));
        Assertions.assertTrue(Task8.secondRegex("1000"));
        Assertions.assertFalse(Task8.secondRegex("100"));
    }

    @Test
    @DisplayName("check if number if '0' in string is mutiple of 3")
    void regex3Test() {
        Assertions.assertTrue(Task8.thirdRegex("101010"));
        Assertions.assertFalse(Task8.thirdRegex("100"));
    }

    @Test
    @DisplayName("check if string is not 11 or 111")
    void regex4Test() {
        Assertions.assertTrue(Task8.fourthRegex("1111"));
        Assertions.assertFalse(Task8.fourthRegex("111"));
        Assertions.assertFalse(Task8.fourthRegex("11"));
    }

    @Test
    @DisplayName("check if {0, 1} alphabet in string")
    void alphabetTest() {
        Assertions.assertFalse(Task8.firstRegex("abcde"));
        Assertions.assertFalse(Task8.secondRegex("abcde"));
        Assertions.assertFalse(Task8.thirdRegex("abcde"));
        Assertions.assertFalse(Task8.fourthRegex("abcde"));
    }
}
