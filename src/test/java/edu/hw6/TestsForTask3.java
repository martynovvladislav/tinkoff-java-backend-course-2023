package edu.hw6;

import edu.hw6.task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestsForTask3 {
    @Test
    @DisplayName("filters test")
    void filtersTest() {
        List<Path> files = Task3.filterDirectory();
        Assertions.assertEquals(files, new ArrayList<Path>(List.of(Path.of("src/main/resources/hw6/task3/verybigfile.png"))));
    }
}
