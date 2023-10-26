package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static Map<Object, Integer> freqDict(List<Object> objectList) {
        if (objectList == null) {
            throw new IllegalArgumentException();
        }

        Map<Object, Integer> frequency = new HashMap<>();
        for (Object object : objectList) {
            if (frequency.containsKey(object)) {
                frequency.put(object, frequency.get(object) + 1);
            } else {
                frequency.put(object, 1);
            }
        }
        return frequency;
    }
}
