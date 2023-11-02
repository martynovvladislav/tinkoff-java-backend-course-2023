package edu.hw3;

import java.util.TreeMap;

public class Task7 {
    private Task7() {
    }

    public static TreeMap<String, String> addNull() {
        TreeMap<String, String> treeMap = new TreeMap<>((str1, str2) -> {
            if (str1 == null && str2 == null) {
                return 0;
            } else if (str1 == null) {
                return -1;
            } else if (str2 == null) {
                return 1;
            }
            return str1.compareTo(str2);
        });
        treeMap.put(null, "test");
        return treeMap;
    }
}
