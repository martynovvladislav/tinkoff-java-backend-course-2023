package edu.hw8;

import edu.hw8.task2.FibonacciCalculator;
import edu.hw8.task2.FixedThreadPool;
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
import java.util.stream.Stream;

public class TestsForTask2 {
    Map<Integer, Integer> fib = new HashMap<>(Map.of(1, 1, 2, 1, 3, 2, 4, 3, 5, 5));
    @Test
    @DisplayName("fixed thread pool test with fibonacci calculator")
    void FibonacciFixedThreadPoolTest() {
        int threads = 8;
        try (FixedThreadPool fixedThreadPool = new FixedThreadPool(threads)) {
            fixedThreadPool.start();
            for (int i = 1; i < 6; i++) {
                int finalI = i;
                fixedThreadPool.execute(() -> {
                    FibonacciCalculator fibonacciCalculator = new FibonacciCalculator(finalI);
                    fibonacciCalculator.run();
                    Assertions.assertEquals(fibonacciCalculator.result, fib.get(finalI));
                });
            }

        }

    }
}
