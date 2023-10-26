package edu.hw3;

import java.util.Map;
import java.util.TreeMap;

public class Task4 {
    private Task4() {
    }

    private final static int MAX_NUMBER = 3999;

    @SuppressWarnings("checkstyle:MagicNumber")
    public static String convertToRoman(int number) {
        if (number < 0 || number > MAX_NUMBER) {
            throw new IllegalArgumentException();
        }

        TreeMap<Integer, String> romanDict = new TreeMap<>(Map.of(1, "I", 4, "IV", 5, "V", 9, "IX", 10, "X", 40, "XL"));
        romanDict.put(1000, "M");
        romanDict.put(900, "CM");
        romanDict.put(500, "D");
        romanDict.put(400, "CD");
        romanDict.put(100, "C");
        romanDict.put(90, "XC");
        romanDict.put(50, "L");

        int currentValue = number;
        StringBuilder romanNumber = new StringBuilder();
        for (var entry : romanDict.descendingMap().entrySet()) {
            while (currentValue >= entry.getKey()) {
                romanNumber.append(entry.getValue());
                currentValue -= entry.getKey();
            }
        }

        return String.valueOf(romanNumber);
    }
}
