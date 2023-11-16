package edu.hw6;

import edu.hw6.task4.Task4;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestsForTask4 {
    @Test
    @DisplayName("output stream chain test")
    void OutputStreamChainTest() throws IOException {
        Path path = Path.of("src/main/resources/hw6/task4");
        FileUtils.cleanDirectory(new File(String.valueOf(path)));
        Task4.createOutputStreamChain();
        Assertions.assertTrue(Files.exists(Path.of(path + "/data.txt")));
        Assertions.assertEquals(
            "Programming is learned by writing programs. ― Brian Kernighan\r\n",
            Files.readString(Path.of(path + "/data.txt"))
        );
    }
}
