package edu.hw9.task1;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class AverageHandler implements Callable<Double> {
    private final double[] data;

    public AverageHandler(double[] inputData) {
        this.data = inputData;
    }

    @Override
    public Double call() {
        return Arrays.stream(data).average().orElseThrow();
    }
}
