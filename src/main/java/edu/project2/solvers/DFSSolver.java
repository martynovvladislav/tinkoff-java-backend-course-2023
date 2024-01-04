package edu.project2.solvers;

import edu.project2.exceptions.IllegalCoordinatesException;
import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSSolver implements MazeSolver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate startCoords, Coordinate endCoords) {
        Coordinate start;
        Coordinate end;
        try {
            correctMaze(maze);
            start = correctCoordinates(startCoords, maze);
            end = correctCoordinates(endCoords, maze);
        } catch (IllegalCoordinatesException | IllegalMazeArgumentException e) {
            throw e;
        }
        Stack<Cell> path = new Stack<>();
        List<Cell> visited = new ArrayList<>();
        Cell[][] grid = maze.getGrid();
        path.push(grid[start.row()][start.col()]);
        while (!path.empty()) {
            Cell current = path.peek();
            visited.add(current);
            if (current.row() == end.row() && current.col() == end.col()) {
                return getDFSPath(path, maze);
            }
            path = processNeighbours(current, visited, maze, path);
        }
        return null;
    }

    private Stack<Cell> processNeighbours(Cell current, List<Cell> visited, Maze maze, Stack<Cell> path) {
        Cell[][] grid = maze.getGrid();
        if (current.row() != 0 && !visited.contains(grid[current.row() - 2][current.col()])
            && grid[current.row() - 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
            path.push(grid[current.row() - 2][current.col()]);
        } else if (current.row() != maze.getHeight() - 1
            && !visited.contains(grid[current.row() + 2][current.col()])
            && grid[current.row() + 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
            path.push(grid[current.row() + 2][current.col()]);
        } else if (current.col() != 0 && !visited.contains(grid[current.row()][current.col() - 2])
            && grid[current.row()][current.col() - 1].type().equals(Cell.Type.BROKEN_WALL)) {
            path.push(grid[current.row()][current.col() - 2]);
        } else if (current.col() != maze.getWidth() - 1 && !visited.contains(grid[current.row()][current.col() + 2])
            && grid[current.row()][current.col() + 1].type().equals(Cell.Type.BROKEN_WALL)) {
            path.push(grid[current.row()][current.col() + 2]);
        } else {
            path.pop();
        }
        return path;
    }

    private List<Coordinate> getDFSPath(Stack<Cell> path, Maze maze) {
        List<Coordinate> coordsPath = new ArrayList<>();
        while (!path.empty()) {
            coordsPath.add(new Coordinate(path.peek().row(), path.pop().col()));
        }
        return coordsPath;
    }
}
