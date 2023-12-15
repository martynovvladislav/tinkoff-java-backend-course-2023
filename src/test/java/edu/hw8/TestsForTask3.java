package edu.hw8;

import edu.hw8.task3.MultiThreadPasswordSolver;
import edu.hw8.task3.SingleThreadPasswordSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class TestsForTask3 {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("one thread password solve test")
    void OneThreadPasswordSolve() {
        SingleThreadPasswordSolver solver = new SingleThreadPasswordSolver();
        String passwords = "v.d.martynov 76d80224611fc919a5d54f0ff9fba446\n" +
            "i.i.ivanov 02c425157ecd32f259548b33402ff6d3";
        solver.readDatabase(passwords);
        long startTime = System.nanoTime();
        solver.generatePasswords("");
        long endTime = System.nanoTime();
        Assertions.assertEquals(solver.getFoundPasswords(), Map.of("v.d.martynov", "qwe", "i.i.ivanov", "zzzz"));
        LOGGER.info((endTime - startTime) / Math.pow(10, 9));
    }

    @Test
    @DisplayName("multi thread password solve test")
    void MultiThreadPasswordSolve() {
        MultiThreadPasswordSolver solver = new MultiThreadPasswordSolver();
        String passwords = "v.d.martynov 76d80224611fc919a5d54f0ff9fba446\n" +
            "i.i.ivanov 02c425157ecd32f259548b33402ff6d3";
        solver.readDatabase(passwords);
        long startTime = System.nanoTime();
        solver.generatePasswords();
        long endTime = System.nanoTime();
        Assertions.assertEquals(MultiThreadPasswordSolver.FOUND_PASSWORDS, Map.of("v.d.martynov", "qwe", "i.i.ivanov", "zzzz"));
        LOGGER.info((endTime - startTime) / Math.pow(10, 9));
    }
}
