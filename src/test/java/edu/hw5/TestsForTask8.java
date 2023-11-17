package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask8 {
    @Test
    @DisplayName("check is string length is odd test")
    void isOddLengthRegexTest() {
        Assertions.assertTrue(Task8.isOddLengthRegex("101"));
        Assertions.assertFalse(Task8.isOddLengthRegex("1000"));
    }

    @Test
    @DisplayName("check if string starts with 0 and has an odd length OR starts with 1 and has an even length test")
    void isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLengthTest() {
        Assertions.assertTrue(Task8.isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength("010"));
        Assertions.assertFalse(Task8.isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength("110"));
        Assertions.assertTrue(Task8.isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength("1000"));
        Assertions.assertFalse(Task8.isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength("100"));
    }

    @Test
    @DisplayName("check if number if '0' in string is mutiple of 3")
    void isAmountOfZerosMultipleByThreeTest() {
        Assertions.assertTrue(Task8.isAmountOfZerosMultipleByThree("101010"));
        Assertions.assertFalse(Task8.isAmountOfZerosMultipleByThree("100"));
    }

    @Test
    @DisplayName("check if string is not 11 or 111")
    void isAnyStringBesidesTwoOrThreeOnesStringTest() {
        Assertions.assertTrue(Task8.isAnyStringBesidesTwoOrThreeOnesString("1111"));
        Assertions.assertFalse(Task8.isAnyStringBesidesTwoOrThreeOnesString("111"));
        Assertions.assertFalse(Task8.isAnyStringBesidesTwoOrThreeOnesString("11"));
    }

    @Test
    @DisplayName("check if {0, 1} alphabet in string")
    void alphabetTest() {
        Assertions.assertFalse(Task8.isOddLengthRegex("abcde"));
        Assertions.assertFalse(Task8.isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength("abcde"));
        Assertions.assertFalse(Task8.isAmountOfZerosMultipleByThree("abcde"));
        Assertions.assertFalse(Task8.isAnyStringBesidesTwoOrThreeOnesString("abcde"));
    }
}
