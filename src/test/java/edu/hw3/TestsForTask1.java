package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask1 {
    @Test
    @DisplayName("null argument test")
    void test1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task1.atbash(null));
    }

    @Test
    @DisplayName("small text with different symbols test")
    void test2() {
        Assertions.assertEquals(Task1.atbash("Hello world!"), "Svool dliow!");
    }

    @Test
    @DisplayName("big text with different symbols test")
    void test3() {
        String inputData = "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler";
        String outputData = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        Assertions.assertEquals(Task1.atbash(inputData), outputData);
    }
}
