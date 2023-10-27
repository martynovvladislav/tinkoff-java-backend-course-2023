package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsForTask3 {
    @Test
    @DisplayName("null argument test")
    void nullArgsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task3.freqDict(null));
    }

    static Arguments[] objectLists() {
        ArrayList<String> list1 = new ArrayList<>(List.of("a", "bb", "a", "bb"));
        Map<String, Integer> dict1 = new HashMap<>(Map.of("bb", 2, "a", 2));

        ArrayList<String> list2 = new ArrayList<>(List.of("this", "and", "that", "and"));
        Map<String, Integer> dict2 = new HashMap<>(Map.of("that", 1, "and", 2, "this", 1));

        ArrayList<String> list3 = new ArrayList<>(List.of("код", "код", "код", "bug"));
        Map<String, Integer> dict3 = new HashMap<>(Map.of("код", 3, "bug", 1));

        ArrayList<Integer> list4 = new ArrayList<>(List.of(1, 1, 2, 2));
        Map<Integer, Integer> dict4 = new HashMap<>(Map.of(1, 2, 2, 2));

        return new Arguments[]{
            Arguments.of(list1, dict1),
            Arguments.of(list2, dict2),
            Arguments.of(list3, dict3),
            Arguments.of(list4, dict4)
        };
    }

    @ParameterizedTest
    @DisplayName("test with different objects")
    @MethodSource("objectLists")
    void differentObjectsInputTest(List<Object> objectList, Map<Object, Integer> ansDict) {
        Assertions.assertEquals(ansDict, Task3.freqDict(objectList));
    }
}
