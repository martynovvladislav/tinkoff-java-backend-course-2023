package edu.project2.renderers;

import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRenderer {
    private static final Logger LOGGER = LogManager.getLogger();

    public String render(Maze maze) {
        if (maze == null || maze.getGrid() == null || maze.getHeight() <= 0 || maze.getWidth() <= 0) {
            throw new IllegalMazeArgumentException();
        }
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
                rendered.append(getGridSymbol(maze.getGrid()[i][j].type()));
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
        return String.valueOf(rendered);
    }

    public String render(Maze maze, List<Coordinate> coordsPath) {
        Cell[][] grid = maze.getGrid();
        for (Coordinate coordinate : coordsPath) {
            grid[coordinate.row()][coordinate.col()] = new Cell(coordinate.row(), coordinate.col(), Cell.Type.PATH);
        }

        return render(new Maze(maze.getHeight(), maze.getWidth(), grid));
    }

    private static String getGridSymbol(Cell.Type type) {
        StringBuilder outputData = new StringBuilder();
        switch (type) {
            case CROSS_WALL -> outputData.append('+');
            case VERTICAL_WALL -> outputData.append("|");
            case HORIZONTAL_WALL -> outputData.append("-");
            case PATH -> outputData.append("*");
            default -> outputData.append(" ");
        }
        return String.valueOf(outputData);
    }
}
