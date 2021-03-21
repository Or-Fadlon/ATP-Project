package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);

        Random random = new Random();
            for (int i = 1; i < rows; i++) {
                maze.addWall(new Position(i, random.nextInt(columns - 1)));
            }
            maze.setStartPosition(new Position(0, random.nextInt(columns - 1)));
            maze.setGoalPosition(new Position(random.nextInt(rows - 1) + 1, columns - 1));

        return maze;
    }
}

