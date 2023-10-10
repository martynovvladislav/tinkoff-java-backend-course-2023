package edu.hw1;

import static java.lang.Math.abs;

public class Task2 {
    private Task2() {}

    @SuppressWarnings("checkstyle:MagicNumber") public static int countDigits(int number) {
        int numberChange = number;
        if (numberChange == 0) {
            return 1;
        }
        numberChange = abs(numberChange);
        int count = 0;
        while (numberChange > 0) {
            count++;
            numberChange /= 10;
        }
        return count;
    }
}
