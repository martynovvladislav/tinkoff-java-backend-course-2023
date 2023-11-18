package edu.project3;

import edu.project3.parsers.PathParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestsForPathParser {
    @Test
    @DisplayName("files path parse test")
    void parseFilePathsTest() {
        List<Path> expected = new ArrayList<>(List.of(
            Path.of("src/main/resources/project3/logs/2021/emptylogs.txt"),
            Path.of("src/main/resources/project3/logs/2022/logs1.txt"),
            Path.of("src/main/resources/project3/logs/2023/logs.txt")
            ));
        Assertions.assertEquals(expected, PathParser.parseFilePaths("logs/*"));
    }

    @Test
    @DisplayName("negative file path test")
    void parseNegativeFilePathsTest() {
        Assertions.assertTrue(PathParser.parseFilePaths("123").isEmpty());
    }
}
