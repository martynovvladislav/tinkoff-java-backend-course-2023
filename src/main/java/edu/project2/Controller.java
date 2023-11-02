package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private Controller() {}

    private static final Logger LOGGER = LogManager.getLogger();

    public static void run() {
        Maze kruskalMaze = MazeGenerator.generateKruskalMaze(6, 9);
        Maze binaryTreeMaze = MazeGenerator.generatebinaryTreeMaze(6, 9);
        LOGGER.info("BINARY TREE MAZE:");
        MazeRenderer.render(binaryTreeMaze);
        LOGGER.info("DFS SOLUTION:");
        MazeSolver.solveDFS(binaryTreeMaze, new Coordinate(0, 0), new Coordinate(10, 16));
        LOGGER.info("BFS SOLUTION:");
        MazeSolver.solveBFS(binaryTreeMaze, new Coordinate(0, 0), new Coordinate(10, 16));

        LOGGER.info("KRUSKAL'S MAZE:");
        MazeRenderer.render(kruskalMaze);
        LOGGER.info("DFS SOLUTION:");
        MazeSolver.solveDFS(kruskalMaze, new Coordinate(0, 0), new Coordinate(10, 16));
        LOGGER.info("BFS SOLUTION:");
        MazeSolver.solveBFS(kruskalMaze, new Coordinate(0, 0), new Coordinate(10, 16));
    }
}
