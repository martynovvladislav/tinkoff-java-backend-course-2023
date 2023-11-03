package edu.project2;

import edu.project2.exceptions.IllegalCoordinatesException;
import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderers.MazeRenderer;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.DFSSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestsForMazeSolver {
    @Test
    @DisplayName("dfs algorithm test")
    void testDFS() {
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else if (i % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                } else if(j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                }
            }
        }
        List<Coordinate> pathWay = new ArrayList<>(List.of(
            new Coordinate(0, 0),
            new Coordinate(0, 2),
            new Coordinate(2, 2),
            new Coordinate(4, 2),
            new Coordinate(4, 4)
        ));

        for (int i = 1; i < pathWay.size(); i++) {
            if (pathWay.get(i - 1).row() < pathWay.get(i).row()) {
                grid[pathWay.get(i).row() - 1][pathWay.get(i).col()] = new Cell(
                    pathWay.get(i).row() - 1, pathWay.get(i).col(), Cell.Type.BROKEN_WALL
                );
            } else {
                grid[pathWay.get(i).row()][pathWay.get(i).col() - 1] = new Cell(
                    pathWay.get(i).row(), pathWay.get(i).col() - 1, Cell.Type.BROKEN_WALL
                );
            }
        }
        Maze maze = new Maze(5, 5, grid);
        List<Coordinate> generatedPathWay = new DFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(2, 2));
        Assertions.assertEquals(pathWay, generatedPathWay.reversed());
    }

    @Test
    @DisplayName("bfs algorithm test")
    void testBFS() {
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else if (i % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                } else if(j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                }
            }
        }
        List<Coordinate> pathWay = new ArrayList<>(List.of(
            new Coordinate(0, 0),
            new Coordinate(0, 2),
            new Coordinate(2, 2),
            new Coordinate(4, 2),
            new Coordinate(4, 4)
        ));

        for (int i = 1; i < pathWay.size(); i++) {
            if (pathWay.get(i - 1).row() < pathWay.get(i).row()) {
                grid[pathWay.get(i).row() - 1][pathWay.get(i).col()] = new Cell(
                    pathWay.get(i).row() - 1, pathWay.get(i).col(), Cell.Type.BROKEN_WALL
                );
            } else {
                grid[pathWay.get(i).row()][pathWay.get(i).col() - 1] = new Cell(
                    pathWay.get(i).row(), pathWay.get(i).col() - 1, Cell.Type.BROKEN_WALL
                );
            }
        }
        Maze maze = new Maze(5, 5, grid);
        List<Coordinate> generatedPathWay = new BFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(2, 2));
        Assertions.assertEquals(pathWay, generatedPathWay.reversed());
    }

    public static Stream<Arguments> negativeArguments() {
        return Stream.of(
            Arguments.of(null, new Coordinate(0, 0), new Coordinate(1, 1)),
            Arguments.of(new Maze(-1, -1, null), new Coordinate(0, 0), new Coordinate(1, 1))
        );
    }

    @ParameterizedTest
    @DisplayName("negative maze input in bfs and dfs")
    @MethodSource("negativeArguments")
    void negativeMazeInputTest(Maze maze, Coordinate start, Coordinate end) {
        Assertions.assertThrows(IllegalMazeArgumentException.class, () -> new BFSSolver().solve(maze, start, end));
        Assertions.assertThrows(IllegalMazeArgumentException.class, () -> new DFSSolver().solve(maze, start, end));
    }

    @Test
    @DisplayName("negative coordinates input in bfs and dfs")
    void negativeCoordinatesInputTest() {
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else if (i % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                } else if(j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                }
            }
        }
        Maze maze = new Maze(5, 5, grid);
        Assertions.assertThrows(IllegalCoordinatesException.class, () ->
            new BFSSolver().solve(maze, new Coordinate(-1, -1), new Coordinate(1, 1)));
        Assertions.assertThrows(IllegalCoordinatesException.class, () ->
            new DFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(600, 600)));
    }

    @Test
    @DisplayName("pathway not found in bfs and dfs test")
    void pathwayNotFoundTest() {
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else if (i % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                } else if(j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                }
            }
        }
        Maze maze = new Maze(5, 5, grid);
        Assertions.assertNull(new BFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(1, 1)));
        Assertions.assertNull(new DFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(1, 1)));
    }
}
