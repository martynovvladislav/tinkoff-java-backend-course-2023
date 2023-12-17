package edu.hw7;

import edu.hw7.task1.MultiThreadCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class TestsForTask1 {
    private static final int THREADS_AMOUNT = 5;
    @Test
    @DisplayName("multi thread counter test")
    void multiThreadCountTest() throws InterruptedException {
        MultiThreadCounter counter = new MultiThreadCounter();
        ExecutorService service = Executors.newFixedThreadPool(THREADS_AMOUNT);
        CountDownLatch countDownLatch = new CountDownLatch(THREADS_AMOUNT);
        Callable<Void> callable = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
            countDownLatch.countDown();
            return null;
        };
        var tasks = Stream.generate(() -> callable).limit(THREADS_AMOUNT).toList();
        service.invokeAll(tasks);
        countDownLatch.await();
        service.shutdown();
        Assertions.assertEquals(counter.get(), THREADS_AMOUNT * 1000);
    }
}
