package edu.hw9.task1;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class MinimumHandler implements Callable<Double> {
    private final double[] data;

    public MinimumHandler(double[] inputData) {
        this.data = inputData;
    }

    @Override
    public Double call() {
        return Arrays.stream(data).min().orElseThrow();
    }
}
