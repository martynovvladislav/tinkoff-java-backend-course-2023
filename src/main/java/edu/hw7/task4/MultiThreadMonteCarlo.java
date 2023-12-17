package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiThreadMonteCarlo {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int THREADS_AMOUNT = 8;

    @SuppressWarnings("checkstyle:MagicNumber")
    public double calculatePi(int iterationsAmount) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_AMOUNT);
        Callable<Integer> task = () -> {
            int circlePoints = 0;
            for (int i = 0; i < iterationsAmount / THREADS_AMOUNT; i++) {
                double x = ThreadLocalRandom.current().nextDouble(2);
                double y = ThreadLocalRandom.current().nextDouble(2);
                if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1) {
                    circlePoints++;
                }
            }
            return circlePoints;
        };
        List<Callable<Integer>> tasks = new ArrayList<>();
        IntStream.range(0, THREADS_AMOUNT).forEach(i -> tasks.add(task));
        List<Future<Integer>> futuresList;
        try {
            futuresList = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        int circlePoints = 0;
        for (Future<Integer> integerFuture : futuresList) {
            try {
                circlePoints += integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        return 4 * ((double) circlePoints / iterationsAmount);
    }


    @SuppressWarnings("checkstyle:MagicNumber")
    public void calculatePiReport(int iterationsAmount) {
        LOGGER.info("Multi-thread PI calculating info:");
        LOGGER.info("Iterations: " + iterationsAmount);
        long startTime = System.nanoTime();
        double calculatedPi = calculatePi(iterationsAmount);
        long finishTime = System.nanoTime();
        LOGGER.info("Calculated PI: " + calculatedPi);
        LOGGER.info("Elapsed time: " + (finishTime - startTime) + "ns");
        LOGGER.info("Inaccuracy: " + Math.abs(Math.PI - calculatedPi) / Math.PI * 100);
    }
}
