package edu.hw6;

import edu.hw6.task2.FileDoesNotExistException;
import edu.hw6.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;

public class TestsForTask2 {
    @Test
    @DisplayName("create file and copies test")
    void fileCloneTest() throws IOException {
        String parent = ("src/main/resources/hw6/task2");
        FileUtils.cleanDirectory(new File(parent));
        Files.createFile(Path.of(parent + "/data.txt"));
        Task2.cloneFile(Path.of(parent + "/data.txt"));
        Assertions.assertTrue(Files.exists(Path.of(parent + "/data - копия.txt")));
        Task2.cloneFile(Path.of(parent + "/data.txt"));
        Assertions.assertTrue(Files.exists(Path.of(parent + "/data - копия (2).txt")));
    }

    @Test
    @DisplayName("file doesnt exist (nothing to copy) test")
    void fileDoesNotExistTest() {
        Assertions.assertThrows(
            FileDoesNotExistException.class,
            () -> Task2.cloneFile(Path.of("src/main/resources/hw6/task2/notdata.txt"))
        );
    }

    @Test
    @DisplayName("files have the same content test")
    void filesHaveSameContentTest() throws IOException {
        String parent = ("src/main/resources/hw6/task2");
        Path path = Path.of(parent + "/data.txt");
        FileUtils.cleanDirectory(new File(parent));
        try (FileWriter fw = new FileWriter(String.valueOf(path))) {
            fw.write("Hello world!");
        }
        Task2.cloneFile(path);
        Assertions.assertEquals(Files.readString(path), Files.readString(Path.of(parent + "/data - копия.txt")));
    }
}
