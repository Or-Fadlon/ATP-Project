package algorithms.mazeGenerators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMazeGeneratorTest {

    @Test
    void generate() {
        MyMazeGenerator mg = new MyMazeGenerator();
        assertTrue(mg.measureAlgorithmTimeMillis(1000, 1000) > 60000);
        Maze maze = mg.generate(1000, 1000);
        assertEquals(maze.getStartPosition(), maze.getGoalPosition());
    }
}