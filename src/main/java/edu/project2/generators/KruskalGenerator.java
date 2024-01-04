package edu.project2.generators;

import edu.project2.exceptions.IllegalHeightOrWidthArgumentsException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.UnionFind;
import java.util.ArrayList;

public class KruskalGenerator implements MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalHeightOrWidthArgumentsException();
        }
        int mazeHeight = height * 2 - 1;
        int mazeWidth = width * 2 - 1;
        Cell[][] maze = new Cell[mazeHeight][mazeWidth];
        int[][] mazeIds = new int[mazeHeight][mazeWidth];
        ArrayList<Coordinate> walls = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                mazeIds[i][j] = id++;
                if ((i % 2 != 0) ^ (j % 2 != 0)) {
                    walls.add(new Coordinate(i, j));
                }
                if (i % 2 != 0 && j % 2 != 0) {
                    maze[i][j] = new Cell(i, j, Cell.Type.CROSS_WALL);
                } else if (i % 2 != 0) {
                    maze[i][j] = new Cell(i, j, Cell.Type.HORIZONTAL_WALL);
                } else if (j % 2 != 0) {
                    maze[i][j] = new Cell(i, j, Cell.Type.VERTICAL_WALL);
                } else {
                    maze[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }
        UnionFind unionFind = new UnionFind(id);

        while (!walls.isEmpty()) {
            Coordinate current = walls.get(RANDOMIZER.nextInt(0, walls.size()));
            switch (maze[current.row()][current.col()].type()) {
                case HORIZONTAL_WALL -> {
                    if (unionFind.find(mazeIds[current.row() - 1][current.col()])
                        == unionFind.find(mazeIds[current.row() + 1][current.col()])) {
                        break;
                    }
                    unionFind.union(mazeIds[current.row() + 1][current.col()],
                        mazeIds[current.row() - 1][current.col()]);
                    maze[current.row()][current.col()] = new Cell(current.row(), current.col(), Cell.Type.BROKEN_WALL);
                }
                case VERTICAL_WALL -> {
                    if (unionFind.find(mazeIds[current.row()][current.col() - 1])
                        == unionFind.find(mazeIds[current.row()][current.col() + 1])) {
                        break;
                    }
                    unionFind.union(mazeIds[current.row()][current.col() + 1],
                        mazeIds[current.row()][current.col() - 1]);
                    maze[current.row()][current.col()] = new Cell(current.row(), current.col(), Cell.Type.BROKEN_WALL);
                }
                default -> {
                    break;
                }
            }
            walls.remove(current);
        }
        return new Maze(mazeHeight, mazeWidth, maze);
    }
}
