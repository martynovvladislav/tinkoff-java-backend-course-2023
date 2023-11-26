package edu.hw7.task4;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OneThreadMonteCarlo {
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("checkstyle:MagicNumber")
    public double calculatePi(int iterationsAmount) {
        int totalPoints = 0;
        int circlePoints = 0;
        Random random = new Random();
        for (int i = 0; i < iterationsAmount; i++) {
            double x = random.nextDouble() * 2;
            double y = random.nextDouble() * 2;
            totalPoints++;
            if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1) {
                circlePoints++;
            }
        }
        return 4 * ((double) circlePoints / totalPoints);
    }


    @SuppressWarnings("checkstyle:MagicNumber")
    public void calculatePiReport(int iterationsAmount) {
        LOGGER.info("Single-thread PI calculating info:");
        LOGGER.info("Iterations: " + iterationsAmount);
        long startTime = System.nanoTime();
        double calculatedPi = calculatePi(iterationsAmount);
        long finishTime = System.nanoTime();
        LOGGER.info("Calculated PI: " + calculatedPi);
        LOGGER.info("Elapsed time: " + (finishTime - startTime) + "ns");
        LOGGER.info("Inaccuracy: " + Math.abs(Math.PI - calculatedPi) / Math.PI * 100);
    }
}
