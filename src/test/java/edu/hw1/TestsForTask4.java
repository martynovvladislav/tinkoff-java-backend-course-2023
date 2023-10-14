package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions.*;

public class TestsForTask4 {
    @Test
    @DisplayName("'123456' test")
    void test1() {
        Assertions.assertThat(Task4.fixString("123456")).isEqualTo("214365");
    }

    @Test
    @DisplayName("'hTsii  s aimex dpus rtni.g' test")
    void test2() {
        Assertions.assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("'badce' test")
    void test3() {
        Assertions.assertThat(Task4.fixString("badce")).isEqualTo("abcde");
    }

    @Test
    @DisplayName("'a'(one letter) test")
    void test4() {
        Assertions.assertThat(Task4.fixString("a")).isEqualTo("a");
    }

    @Test
    @DisplayName("null input test")
    void test5() {
        Assertions.assertThat(Task4.fixString(null)).isEqualTo(null);
    }
}
