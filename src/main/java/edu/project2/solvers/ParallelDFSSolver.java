package edu.project2.solvers;

import edu.project2.exceptions.IllegalCoordinatesException;
import edu.project2.exceptions.IllegalMazeArgumentException;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelDFSSolver implements MazeSolver {
    private List<Cell> visited;
    private ForkJoinPool forkJoinPool;
    private Maze maze;

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
        visited = new ArrayList<>();
        this.maze = maze;
        Cell[][] grid = maze.getGrid();
        forkJoinPool = new ForkJoinPool();
        Solver solver = new Solver(grid, grid[start.row()][start.col()], end);
        return forkJoinPool.invoke(solver);
    }

    private List<Coordinate> processNeighbours(Cell current, Coordinate end) {
        List<Solver> paths = new ArrayList<>();
        Cell[][] grid = maze.getGrid();
        if (current.row() != 0 && !visited.contains(grid[current.row() - 2][current.col()])
            && grid[current.row() - 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
            Solver task = new Solver(grid, grid[current.row() - 2][current.col()], end);
            task.fork();
            paths.add(task);
        }
        if (current.row() != maze.getHeight() - 1
            && !visited.contains(grid[current.row() + 2][current.col()])
            && grid[current.row() + 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
            Solver task = new Solver(grid, grid[current.row() + 2][current.col()], end);
            task.fork();
            paths.add(task);
        }
        if (current.col() != 0 && !visited.contains(grid[current.row()][current.col() - 2])
            && grid[current.row()][current.col() - 1].type().equals(Cell.Type.BROKEN_WALL)) {
            Solver task = new Solver(grid, grid[current.row()][current.col() - 2], end);
            task.fork();
            paths.add(task);
        }
        if (current.col() != maze.getWidth() - 1 && !visited.contains(grid[current.row()][current.col() + 2])
            && grid[current.row()][current.col() + 1].type().equals(Cell.Type.BROKEN_WALL)) {
            Solver task = new Solver(grid, grid[current.row()][current.col() + 2], end);
            task.fork();
            paths.add(task);
        }
        for (Solver path : paths) {
            List<Coordinate> pathCoords = path.join();
            if (pathCoords != null) {
                return pathCoords;
            }
        }
        return null;
    }

    class Solver extends RecursiveTask<List<Coordinate>> {
        private final Cell[][] grid;
        private final Coordinate end;
        private final Cell current;

        Solver(Cell[][] grid, Cell current, Coordinate end) {
            this.grid = grid;
            this.current = current;
            this.end = end;
        }

        @Override
        protected List<Coordinate> compute() {
            visited.add(current);
            if (current.col() == end.col() && current.row() == end.row()) {
                return new ArrayList<>(List.of(new Coordinate(current.row(), current.col())));
            }
            List<Coordinate> path = processNeighbours(current, end);
            if (path != null) {
                path.add(new Coordinate(current.row(), current.col()));
                return path;
            }
            return null;
        }
    }
}
