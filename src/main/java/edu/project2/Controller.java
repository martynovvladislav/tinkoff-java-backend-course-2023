package edu.project2;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.generators.KruskalGenerator;
import edu.project2.generators.MazeGenerator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderers.MazeRenderer;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.DFSSolver;
import edu.project2.solvers.ParallelDFSSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private Controller() {
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAZE_HEIGHT = 15;
    private static final int MAZE_WIDTH = 70;

    public static void run() {
        MazeGenerator binaryTreeGenerator = new BinaryTreeGenerator();
        MazeGenerator kruskalGenerator = new KruskalGenerator();
        LOGGER.info("BINARY TREE MAZE:");
        demonstrateMaze(binaryTreeGenerator.generate(MAZE_HEIGHT, MAZE_WIDTH));
        LOGGER.info("KRUSKAL'S MAZE:");
        demonstrateMaze(kruskalGenerator.generate(MAZE_HEIGHT, MAZE_WIDTH));
    }

    private static void demonstrateMaze(Maze maze) {
        MazeRenderer renderer = new MazeRenderer();
        LOGGER.info(renderer.render(maze));

        LOGGER.info("DFS PARALLEL SOLUTION:");
        LOGGER.info(renderer.render(
            maze,
            new ParallelDFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(MAZE_HEIGHT - 1, MAZE_WIDTH - 1))
        ));

        LOGGER.info("DFS SOLUTION:");
        LOGGER.info(renderer.render(
                maze,
                new DFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(MAZE_HEIGHT - 1, MAZE_WIDTH - 1))
            )
        );

        LOGGER.info("BFS SOLUTION:");
        LOGGER.info(renderer.render(
                maze,
                new BFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(MAZE_HEIGHT - 1, MAZE_WIDTH - 1))
            )
        );
    }
}
