package edu.project2.solvers;

import edu.project2.exceptions.IllegalCoordinatesException;
import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements MazeSolver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate startCoords, Coordinate endCoords) {
        if (maze == null || maze.getGrid() == null || maze.getHeight() <= 0 || maze.getWidth() <= 0) {
            throw new IllegalMazeArgumentException();
        }
        Coordinate start;
        Coordinate end;
        try {
            correctMaze(maze);
            start = correctCoordinates(startCoords, maze);
            end = correctCoordinates(endCoords, maze);
        } catch (IllegalCoordinatesException | IllegalMazeArgumentException e) {
            throw e;
        }
        Queue<Cell> nextCells = new ArrayDeque<>();
        List<Cell> visited = new ArrayList<>();
        Map<Cell, Cell> path = new HashMap<>();
        Cell[][] grid = maze.getGrid();
        nextCells.add(grid[start.row()][start.col()]);
        while (!nextCells.isEmpty()) {
            Cell current = nextCells.poll();
            visited.add(current);
            if (current.row() == end.row() && current.col() == end.col()) {
                return getBFSPath(path, current, start, maze);
            }
            List<Cell> processedNeighboursList = processNeighbours(current, visited, maze);
            for (Cell cell : processedNeighboursList) {
                nextCells.add(grid[cell.row()][cell.col()]);
                path.put(grid[cell.row()][cell.col()], current);
            }
        }
        return null;
    }

    private List<Coordinate> getBFSPath(Map<Cell, Cell> path, Cell currentCell, Coordinate start, Maze maze) {
        Cell current = currentCell;
        List<Coordinate> coordsPath = new ArrayList<>();
        while (current.row() != start.row() || current.col() != start.col()) {
            coordsPath.add(new Coordinate(current.row(), current.col()));
            current = path.get(current);
        }
        coordsPath.add(new Coordinate(current.row(), current.col()));
        return coordsPath;
    }

    private List<Cell> processNeighbours(Cell current, List<Cell> visited, Maze maze) {
        Cell[][] grid = maze.getGrid();
        List<Cell> outputData = new ArrayList<>();
        if (current.row() != 0 && !visited.contains(grid[current.row() - 2][current.col()])
            && grid[current.row() - 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
            outputData.add(grid[current.row() - 2][current.col()]);
        }
        if (current.col() != maze.getWidth() - 1 && !visited.contains(grid[current.row()][current.col() + 2])
            && grid[current.row()][current.col() + 1].type().equals(Cell.Type.BROKEN_WALL)) {
            outputData.add(grid[current.row()][current.col() + 2]);
        }
        if (current.col() != 0 && !visited.contains(grid[current.row()][current.col() - 2])
            && grid[current.row()][current.col() - 1].type().equals(Cell.Type.BROKEN_WALL)) {
            outputData.add(grid[current.row()][current.col() - 2]);
        }
        if (current.row() != maze.getHeight() - 1
            && !visited.contains(grid[current.row() + 2][current.col()])
            && grid[current.row() + 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
            outputData.add(grid[current.row() + 2][current.col()]);
        }
        return outputData;
    }
}
