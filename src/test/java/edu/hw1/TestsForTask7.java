package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask7 {
    @Test
    @DisplayName("rotateRight(8, 1) -> 4 test")
    void test1() {
        assert (Task7.rotateRight(8, 1) == 4);
    }

    @Test
    @DisplayName("rotateRight(8, 6) -> 2 test")
    void test2() {
        assert (Task7.rotateRight(8, 6) == 2);
    }

    @Test
    @DisplayName("rotateLeft(17, 2) -> 6 test")
    void test3() {
        assert (Task7.rotateLeft(17, 2) == 6);
    }

    @Test
    @DisplayName("rotateLeft(16, 1) -> 1 test")
    void test4() {
        assert (Task7.rotateLeft(16, 1) == 1);
    }

    @Test
    @DisplayName("rotateRight(0, 15) -> 0 test")
    void test5() {
        assert (Task7.rotateLeft(0, 15) == 0);
    }
}
