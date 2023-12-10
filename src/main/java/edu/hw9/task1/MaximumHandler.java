package edu.hw9.task1;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class MaximumHandler implements Callable<Double> {
    private final double[] data;

    public MaximumHandler(double[] inputData) {
        this.data = inputData;
    }

    @Override
    public Double call() {
        return Arrays.stream(data).max().orElseThrow();
    }
}
