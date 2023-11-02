package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private MazeSolver() {
    }

    public static boolean solveDFS(Maze maze, Coordinate start, Coordinate end) {
        Stack<Cell> path = new Stack<>();
        List<Cell> visited = new ArrayList<>();
        Cell[][] grid = maze.getGrid();
        path.push(grid[start.row()][start.col()]);
        while (!path.empty()) {
            Cell current = path.peek();
            visited.add(current);
            if (current.row() == end.row() && current.col() == end.col()) {
                List<Coordinate> coordsPath = new ArrayList<>();
                while (!path.empty()) {
                    coordsPath.add(new Coordinate(path.peek().row(), path.pop().col()));
                }
                MazeRenderer.renderPath(maze, coordsPath);
                return true;
            } else if (current.row() != 0 && !visited.contains(grid[current.row() - 2][current.col()])
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
        }
        return false;
    }

    public static boolean solveBFS(Maze maze, Coordinate start, Coordinate end) {
        Queue<Cell> nextCells = new ArrayDeque<>();
        List<Cell> visited = new ArrayList<>();
        Map<Cell, Cell> path = new HashMap<>();
        Cell[][] grid = maze.getGrid();
        nextCells.add(grid[start.row()][start.col()]);
        while (!nextCells.isEmpty()) {
            Cell current = nextCells.poll();
            visited.add(current);
            if (current.row() == end.row() && current.col() == end.col()) {
                List<Coordinate> coordsPath = new ArrayList<>();
                while (current.row() != start.row() || current.col() != start.col()) {
                    coordsPath.add(new Coordinate(current.row(), current.col()));
                    current = path.get(current);
                }
                coordsPath.add(new Coordinate(current.row(), current.col()));
                MazeRenderer.renderPath(maze, coordsPath);
                return true;
            }
            if (current.row() != 0 && !visited.contains(grid[current.row() - 2][current.col()])
                && grid[current.row() - 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
                nextCells.add(grid[current.row() - 2][current.col()]);
                path.put(grid[current.row() - 2][current.col()], current);
            }
            if (current.row() != maze.getHeight() - 1
                && !visited.contains(grid[current.row() + 2][current.col()])
                && grid[current.row() + 1][current.col()].type().equals(Cell.Type.BROKEN_WALL)) {
                nextCells.add(grid[current.row() + 2][current.col()]);
                path.put(grid[current.row() + 2][current.col()], current);
            }
            if (current.col() != 0 && !visited.contains(grid[current.row()][current.col() - 2])
                && grid[current.row()][current.col() - 1].type().equals(Cell.Type.BROKEN_WALL)) {
                nextCells.add(grid[current.row()][current.col() - 2]);
                path.put(grid[current.row()][current.col() - 2], current);
            }
            if (current.col() != maze.getWidth() - 1 && !visited.contains(grid[current.row()][current.col() + 2])
                && grid[current.row()][current.col() + 1].type().equals(Cell.Type.BROKEN_WALL)) {
                nextCells.add(grid[current.row()][current.col() + 2]);
                path.put(grid[current.row()][current.col() + 2], current);
            }
        }
        return false;
    }
}
