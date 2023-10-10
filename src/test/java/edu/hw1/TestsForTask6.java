package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask6 {
    @Test
    @DisplayName("6621 test")
    void test1() {
        assert (Task6.countK(6621) == 5);
    }

    @Test
    @DisplayName("6554 -> 4 test")
    void test2() {
        assert (Task6.countK(6554) == 4);
    }

    @Test
    @DisplayName("1234 -> 3 test")
    void test3() {
        assert (Task6.countK(1234) == 3);
    }

    @Test
    @DisplayName("999 -> 4 test")
    void test4() {
        assert (Task6.countK(999) == 4);
    }

    @Test
    @DisplayName("negative number(-123) test")
    void test5() {
        assert (Task6.countK(-123) == -1);
    }
}
