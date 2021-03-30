package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    MyMazeGenerator mg = new MyMazeGenerator();
    Maze maze = new Maze(10, 10);
    SearchableMaze searchableMaze = new SearchableMaze(maze);
    BestFirstSearch searcher = new BestFirstSearch();
    Solution solution = searcher.solve(searchableMaze);
    ArrayList<AState> solutionPath = solution.getSolutionPath();

    @Test
    void solutionStartGoalPosition() {
        assertEquals(maze.getStartPosition(), solutionPath.get(0).getCurrentState());
        assertEquals(maze.getGoalPosition(), solutionPath.get(solutionPath.size() - 1).getCurrentState());
    }

    @Test
    void validPath() {
        boolean flag = true;
        AState current, prev;
        for (int i = 1; i < solutionPath.size(); i++) {
            flag = true;
            prev = solutionPath.get(i - 1);
            current = solutionPath.get(i);
            if (prev.equals(current))
                flag = false;

            assertTrue(flag);
        }
    }
}