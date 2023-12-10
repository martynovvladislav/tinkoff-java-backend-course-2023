package edu.project2.solvers;

import edu.project2.exceptions.IllegalCoordinatesException;
import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public interface MazeSolver {

    List<Coordinate> solve(Maze maze, Coordinate startCoords, Coordinate endCoords);

    default Coordinate correctCoordinates(Coordinate coordinate, Maze maze) {
        Coordinate newCoordinates = new Coordinate(coordinate.row() * 2, coordinate.col() * 2);
        if (newCoordinates.row() < 0 || newCoordinates.row() >= maze.getHeight()
            || newCoordinates.col() < 0 || newCoordinates.col() >= maze.getWidth()) {
            throw new IllegalCoordinatesException();
        }
        return newCoordinates;
    }

    default void correctMaze(Maze maze) {
        if (maze == null || maze.getGrid() == null || maze.getHeight() <= 0 || maze.getWidth() <= 0) {
            throw new IllegalMazeArgumentException();
        }
    }
}
