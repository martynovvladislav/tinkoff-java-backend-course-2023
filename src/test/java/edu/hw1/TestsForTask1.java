package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestsForTask1 {
    @Test
    @DisplayName("01:00 -> 60 test")
    void test1() {
        assertThat(Task1.minutesToSeconds("01:00") == 60);
    }

    @Test
    @DisplayName("13:56 -> 836 test")
    void test2() {
        assertThat(Task1.minutesToSeconds("13:56") == 836);
    }

    @Test
    @DisplayName("10:60(incorrect) test")
    void test3() {
        assertThat(Task1.minutesToSeconds("10:60") == -1);
    }

    @Test
    @DisplayName("incorrect input test")
    void test4() {
        assertThat(Task1.minutesToSeconds("1030") == -1);
    }
}
