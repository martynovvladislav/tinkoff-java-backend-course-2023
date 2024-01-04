package edu.project2;

import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderers.MazeRenderer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestsForMazeRenderer {

    public static Stream<Arguments> negativeArguments() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of(new Maze(-1, -1, null))
        );
    }

    @ParameterizedTest
    @DisplayName("negative input in maze renderer")
    @MethodSource("negativeArguments")
    void negativeInputTest(Maze maze) {
        Assertions.assertThrows(IllegalMazeArgumentException.class, () -> new MazeRenderer().render(maze));
    }

    @Test
    @DisplayName("render test")
    void renderTest() {
        Cell[][] grid = new Cell[5][5];
        StringBuilder expected = new StringBuilder();
        expected.append("\n+-----+\n");
        for (int i = 0; i < 5; i++) {
            expected.append("|");
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                    expected.append(" ");
                } else if (i % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                    expected.append("|");
                } else if(j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                    expected.append("-");
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                    expected.append("+");
                }
            }
            expected.append("|\n");
        }
        expected.append("+-----+");
        Maze maze = new Maze(5, 5, grid);
        Assertions.assertEquals(new MazeRenderer().render(maze), String.valueOf(expected));
    }

    @Test
    @DisplayName("render with path test")
    void renderWithPathTest() {
        Cell[][] grid = new Cell[3][3];
        StringBuilder expected = new StringBuilder();
        expected.append("\n+---+\n");
        for (int i = 0; i < 3; i++) {
            expected.append("|");
            for (int j = 0; j < 3; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                    expected.append(" ");
                } else if (i % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                    expected.append("|");
                } else if(j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                    expected.append("-");
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                    expected.append("+");
                }
            }
            expected.append("|\n");
        }
        expected.append("+---+");
        expected.setCharAt(8, '*');
        expected.setCharAt(10, '*');
        expected.setCharAt(22, '*');
        Maze maze = new Maze(3, 3, grid);
        Assertions.assertEquals(new MazeRenderer().render(maze, new ArrayList<>(List.of(
            new Coordinate(0, 0),
            new Coordinate(0, 2),
            new Coordinate(2, 2)
        ))), String.valueOf(expected));
    }
}
