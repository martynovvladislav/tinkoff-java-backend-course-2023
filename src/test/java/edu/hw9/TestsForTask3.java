package edu.hw9;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.DFSSolver;
import edu.project2.solvers.ParallelDFSSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TestsForTask3 {
    @Test
    @DisplayName("parallel dfs algorithm test")
    void testParallelDFS() {
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
        List<Coordinate> generatedPathWay = new ParallelDFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(2, 2));
        Assertions.assertEquals(pathWay, generatedPathWay.reversed());
    }
}
