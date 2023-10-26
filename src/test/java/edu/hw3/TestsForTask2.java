package edu.hw3;

import edu.hw3.task2.InvalidBracketsSequenceException;
import edu.hw3.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class TestsForTask2 {
    @Test
    @DisplayName("null argument test")
    void test1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(null));
    }

    static Arguments[] unbalancedSequences() {
        return new Arguments[]{
            Arguments.of("(()))()()"),
            Arguments.of("(()())(()")
        };
    }

    @ParameterizedTest
    @DisplayName("negative input test")
    @MethodSource("unbalancedSequences")
    void test2(String inputSequence) {
        Assertions.assertThrows(InvalidBracketsSequenceException.class, () -> Task2.clusterize(inputSequence));
    }

    static Arguments[] balancedSequences() {
        return new Arguments[]{
            Arguments.of("()()()", new ArrayList<>(List.of("()", "()", "()"))),
            Arguments.of("((()))", new ArrayList<>(List.of("((()))"))),
            Arguments.of("((()))(())()()(()())", new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())"))),
            Arguments.of("((())())(()(()()))", new ArrayList<>(List.of("((())())", "(()(()()))")))
        };
    }

    @ParameterizedTest
    @DisplayName("positive input test")
    @MethodSource("balancedSequences")
    void test3(String inputSequence, List<String> clustersList) {
        Assertions.assertEquals(clustersList, Task2.clusterize(inputSequence));
    }
}
