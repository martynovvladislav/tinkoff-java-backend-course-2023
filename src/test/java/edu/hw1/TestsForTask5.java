package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask5 {
    @Test
    @DisplayName("11211230 test(true)")
    void test1() {
        assert (Task5.isPalindromeDescendant(11211230) == true);
    }

    @Test
    @DisplayName("13001120 test(true)")
    void test2() {
        assert (Task5.isPalindromeDescendant(13001120) == true);
    }

    @Test
    @DisplayName("23336014 test(true)")
    void test3() {
        assert (Task5.isPalindromeDescendant(23336014) == true);
    }

    @Test
    @DisplayName("11 test(true)")
    void test4() {
        assert (Task5.isPalindromeDescendant(11) == true);
    }

    @Test
    @DisplayName("non-palindrome odd length(113) test")
    void test5() {
        assert (Task5.isPalindromeDescendant(113) == false);
    }

    @Test
    @DisplayName("non-palindrome(123456) test")
    void test6() {
        assert (Task5.isPalindromeDescendant(123456) == false);
    }

    @Test
    @DisplayName("palindrome odd length(12343) test(true)")
    void test7() {
        assert (Task5.isPalindromeDescendant(12343) == true);
    }
}
