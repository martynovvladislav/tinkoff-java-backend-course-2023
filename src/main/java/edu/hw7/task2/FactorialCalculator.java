package edu.hw7.task2;

import java.util.stream.IntStream;

public class FactorialCalculator {
    private FactorialCalculator() {}

    public static int calculate(int n) {
        return IntStream.rangeClosed(1, n)
            .parallel()
            .reduce((x1, x2) -> x1 * x2)
            .orElseThrow();
    }
}
