package edu.project2;

import edu.project2.exceptions.IllegalHeightOrWidthArgumentsException;
import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.generators.KruskalGenerator;
import edu.project2.generators.MazeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForMazeGenerator {

    @Test
    @DisplayName("positive input in binary tree maze generator test")
    void positiveInputBinaryTreeGenerator() {
        Assertions.assertDoesNotThrow(() -> {
            MazeGenerator binaryTreeGenerator = new BinaryTreeGenerator();
            binaryTreeGenerator.generate(5, 5);
        });
    }

    @Test
    @DisplayName("negative input in binary tree maze generator test")
    void negativeInputBinaryTreeGenerator() {
        Assertions.assertThrows(IllegalHeightOrWidthArgumentsException.class, () -> {
            MazeGenerator binaryTreeGenerator = new BinaryTreeGenerator();
            binaryTreeGenerator.generate(-5, 6);
        });
    }

    @Test
    @DisplayName("positive input in kruskal maze generator test")
    void positiveInputKruskalGenerator() {
        Assertions.assertDoesNotThrow(() -> {
            MazeGenerator kruskalGenerator = new KruskalGenerator();
            kruskalGenerator.generate(5, 5);
        });
    }

    @Test
    @DisplayName("negative input in kruskal maze generator test")
    void negativeInputKruskalGenerator() {
        Assertions.assertThrows(IllegalHeightOrWidthArgumentsException.class, () -> {
            MazeGenerator kruskalGenerator = new KruskalGenerator();
            kruskalGenerator.generate(-5, 6);
        });
    }
}
