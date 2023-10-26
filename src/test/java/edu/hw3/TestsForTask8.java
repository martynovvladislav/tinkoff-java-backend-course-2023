package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestsForTask8 {
    @Test
    @DisplayName("hasNext test")
    void test1() {
        Iterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        Assertions.assertTrue(backwardIterator.hasNext());
    }

    static Arguments[] objectLists() {
        return new Arguments[] {
            Arguments.of(new ArrayList<>(List.of(1, 2, 3))),
            Arguments.of(new ArrayList<>(List.of("a", "b", "c")))
        };
    }

    @ParameterizedTest
    @DisplayName("objects of different types test")
    @MethodSource("objectLists")
    void test1(List<Object> list) {
        Iterator<Object> backwardIterator = new BackwardIterator<>(list);
        List<Object> outputList = new ArrayList<>();
        while (backwardIterator.hasNext()) {
            outputList.add(backwardIterator.next());
        }
        Assertions.assertEquals(outputList, list.reversed());
    }
}
