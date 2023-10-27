package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

public class TestsForTask7 {
    @Test
    @DisplayName("null insert test")
    void insertNullArgumentTest() {
        TreeMap<String, String> treeMap = Task7.addNull();
        Assertions.assertTrue(treeMap.containsKey(null));
    }
}
