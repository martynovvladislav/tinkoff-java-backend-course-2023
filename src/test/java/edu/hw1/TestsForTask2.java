package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask2 {
    @Test
    @DisplayName("4666 -> 4 test")
    void test1() {
        assert (Task2.countDigits(4666) == 4);
    }

    @Test
    @DisplayName("544 -> 3 test")
    void test2() {
        assert (Task2.countDigits(544) == 3);
    }

    @Test
    @DisplayName("0 -> 1 test")
    void test3() {
        assert (Task2.countDigits(0) == 1);
    }

    @Test
    @DisplayName("negative number(-123) -> 3 test")
    void test4() {
        assert (Task2.countDigits(-123) == 3);
    }
}
