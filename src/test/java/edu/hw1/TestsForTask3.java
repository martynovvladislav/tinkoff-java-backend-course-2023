package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask3 {
    @Test
    @DisplayName("[1, 2, 3, 4], [0, 6] test(true)")
    void test1() {
        assert (Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}) == true);
    }

    @Test
    @DisplayName("[3, 1], [4, 0] test(true)")
    void test2() {
        assert (Task3.isNestable(new int[] {3, 1}, new int[] {4, 0}) == true);
    }

    @Test
    @DisplayName("[9, 9, 8], [8, 9] test(false)")
    void test3() {
        assert (Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9}) == false);
    }

    @Test
    @DisplayName("[1, 2, 3, 4], [2, 3] test(false)")
    void test4() {
        assert (Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}) == false);
    }

    @Test
    @DisplayName("null test")
    void test5() {
        assert (Task3.isNestable(null, null) == false);
    }
}
