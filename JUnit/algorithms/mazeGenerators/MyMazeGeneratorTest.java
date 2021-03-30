package algorithms.mazeGenerators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMazeGeneratorTest {

    MyMazeGenerator mg = new MyMazeGenerator();

    @Test
    void generateTimeLessThen1Min() {
        assertFalse(mg.measureAlgorithmTimeMillis(1000, 1000) > 60000);
    }

    Maze maze = mg.generate(1000, 1000);
    @Test
    void startEqualsGoal() {
        assertNotEquals(maze.getStartPosition(), maze.getGoalPosition());
    }

    @Test
    void startOrGoalBlocked() {
        assertFalse(maze.positionOfWall(maze.getStartPosition()));
        assertFalse(maze.positionOfWall(maze.getGoalPosition()));
    }
}