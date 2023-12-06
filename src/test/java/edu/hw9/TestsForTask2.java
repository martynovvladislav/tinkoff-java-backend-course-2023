package edu.hw9;

import edu.hw9.task2.FileCounter;
import edu.hw9.task2.FileExtensionSearcher;
import edu.hw9.task2.FileSizeSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class TestsForTask2 {
    @Test
    @DisplayName("file counter test")
    void fileCounterTest() {
        File directory = new File("src/main/java/edu/hw2");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileCounter fileCounter = new FileCounter(directory, 2);
        List<File> files = forkJoinPool.invoke(fileCounter);
        Assertions.assertEquals(files, List.of(
            new File("src/main/java/edu/hw2/task3/connectionmanagers"),
            new File("src/main/java/edu/hw2/task3/connections")
        ));
    }

    @Test
    @DisplayName("file extensions searcher test")
    void fileExtensionsSearchTest() {
        File directory = new File("src/main");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileExtensionSearcher fileExtensionSearcher = new FileExtensionSearcher(directory, "txt");
        List<File> files = forkJoinPool.invoke(fileExtensionSearcher);
        Assertions.assertEquals(files, List.of(
            new File("src/main/resources/settings.txt")
        ));
    }

    @Test
    @DisplayName("file sizes searcher test")
    void fileSizeSearchTest() {
        File directory = new File("src/main/java/edu/project1");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileSizeSearcher fileSizeSearcher = new FileSizeSearcher(directory, 2000);
        List<File> files = forkJoinPool.invoke(fileSizeSearcher);
        Assertions.assertEquals(files, List.of(
            new File("src/main/java/edu/project1/FileHandler.java"),
            new File("src/main/java/edu/project1/Game.java")
        ));
    }
}
