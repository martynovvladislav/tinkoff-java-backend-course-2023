package edu.project2.generators;

import edu.project2.exceptions.IllegalHeightOrWidthArgumentsException;
import edu.project2.maze.Cell;
import edu.project2.maze.Maze;

public class BinaryTreeGenerator implements MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalHeightOrWidthArgumentsException();
        }
        int mazeHeight = height * 2 - 1;
        int mazeWidth = width * 2 - 1;
        Cell[][] maze = new Cell[mazeHeight][mazeWidth];
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    maze[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                } else {
                    maze[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }
        for (int i = 0; i < mazeHeight; i += 2) {
            for (int j = 0; j < mazeWidth; j += 2) {
                maze[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                if (i == mazeHeight - 1 && j != mazeWidth - 1) {
                    maze[i][j + 1] = new Cell(i, j, Cell.Type.BROKEN_WALL);
                } else if (j == mazeWidth - 1 && i != mazeHeight - 1) {
                    maze[i + 1][j] = new Cell(i, j, Cell.Type.BROKEN_WALL);
                } else if (!(i == mazeHeight - 1 && j == mazeWidth - 1)) {
                    if (RANDOMIZER.nextBoolean()) {
                        maze[i + 1][j] = new Cell(i, j, Cell.Type.BROKEN_WALL);
                        maze[i][j + 1] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                    } else {
                        maze[i][j + 1] = new Cell(i, j, Cell.Type.BROKEN_WALL);
                        maze[i + 1][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                    }
                }
            }
        }
        return new Maze(mazeHeight, mazeWidth, maze);
    }
}
