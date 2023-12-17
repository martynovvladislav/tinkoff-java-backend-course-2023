package edu.hw10;

import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.FibCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask2 {
    class FibCalculatorImpl implements FibCalculator {
        @Override
        public long fib(int number) {
            if (number <= 1) {
                return number;
            } else {
                return fib(number - 1) + fib(number - 2);
            }
        }
    }

    @Test
    @DisplayName("cache proxy test")
    void cacheProxyTest() {
        FibCalculator fibCalculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);

        long result = proxy.fib(5);
        Assertions.assertEquals(result, 5);

        result = proxy.fib(6);
        Assertions.assertEquals(result, 8);

        result = proxy.fib(5);
        Assertions.assertEquals(result, 5);
    }
}
