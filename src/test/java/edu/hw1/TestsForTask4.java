package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask4 {
    @Test
    @DisplayName("'123456' test")
    void test1() {
        assert (Task4.fixString("123456").equals("214365"));
    }

    @Test
    @DisplayName("'hTsii  s aimex dpus rtni.g' test")
    void test2() {
        assert (Task4.fixString("hTsii  s aimex dpus rtni.g").equals("This is a mixed up string."));
    }

    @Test
    @DisplayName("'badce' test")
    void test3() {
        assert (Task4.fixString("badce").equals("abcde"));
    }

    @Test
    @DisplayName("'a'(one letter) test")
    void test4() {
        assert (Task4.fixString("a").equals("a"));
    }

    @Test
    @DisplayName("null input test")
    void test5() {
        assert (Task4.fixString(null) == null);
    }
}
