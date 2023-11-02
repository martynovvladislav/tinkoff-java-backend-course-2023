package edu.project2;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRenderer {
    private MazeRenderer() {}

    private static final Logger LOGGER = LogManager.getLogger();

    public static void render(Maze maze) {
        StringBuilder rendered = new StringBuilder("\n");
        for (int i = 0; i < maze.getWidth() + 2; i++) {
            if (i == 0 || i == maze.getWidth() + 1) {
                rendered.append("+");
            } else {
                rendered.append("-");
            }
        }
        rendered.append("\n");
        for (int i = 0; i < maze.getHeight(); i++) {
            rendered.append("|");
            for (int j = 0; j < maze.getWidth(); j++) {
                switch (maze.getGrid()[i][j].type()) {
                    case CROSS_WALL -> rendered.append("+");
                    case VERTICAL_WALL -> rendered.append("|");
                    case HORIZONTAL_WALL -> rendered.append("-");
                    case PATH -> rendered.append("*");
                    default -> rendered.append(" ");
                }
            }
            rendered.append("|\n");
        }
        for (int i = 0; i < maze.getWidth() + 2; i++) {
            if (i == 0 || i == maze.getWidth() + 1) {
                rendered.append("+");
            } else {
                rendered.append("-");
            }
        }
        LOGGER.info(String.valueOf(rendered));
    }

    public static void renderPath(Maze maze, List<Coordinate> coordsPath) {
        Cell[][] grid = maze.getGrid();
        for (Coordinate coordinate : coordsPath) {
            grid[coordinate.row()][coordinate.col()] = new Cell(coordinate.row(), coordinate.col(), Cell.Type.PATH);
        }

        render(new Maze(maze.getHeight(), maze.getWidth(), grid));
    }
}
