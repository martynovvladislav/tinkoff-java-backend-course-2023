package edu.hw9;

import edu.hw9.task1.StatsCollector;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask1 {
    @Test
    @DisplayName("Statistics collector test")
    void statisticsCollectorTest() throws InterruptedException {
        StatsCollector collector = new StatsCollector();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(() -> collector.push("data1", new double[] {0.1, 0.2, 1.4, 5.1, 0.3}));
        executorService.execute(() -> collector.push("data2", new double[] {1, 2, 3, 4, 5}));
        executorService.shutdown();
        Thread.sleep(100);
        //Формат: Название метрики, данные в порядке "сумма, среднее, максимум, минимум"
        Assertions.assertEquals(collector.stats(), new HashMap<>(Map.of(
            "data1", List.of(7.1, 1.42, 5.1, 0.1),
            "data2", List.of(15.0, 3.0, 5.0, 1.0)
        )));
    }
}
