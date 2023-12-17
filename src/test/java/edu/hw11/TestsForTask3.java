package edu.hw11;

import edu.hw11.task3.FibonacciClassGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestsForTask3 {
    @Test
    @DisplayName("create fibonacci calculator class test")
    void createFibCalculatorTest() {
        try {
            Class<?> clazz = FibonacciClassGenerator.createFibonacciClass();
            Object fibInstance = clazz.getConstructor().newInstance();
            Method fibMethod = clazz.getMethod("fib", int.class);
            Assertions.assertEquals((long) 5, fibMethod.invoke(fibInstance, 5));
            Assertions.assertEquals((long) 8, fibMethod.invoke(fibInstance, 6));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
