package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    MyMazeGenerator mg = new MyMazeGenerator();
    Maze maze = null;
    SearchableMaze searchableMaze = new SearchableMaze(maze);
    BestFirstSearch searcher = new BestFirstSearch();
    Solution solution = searcher.solve(searchableMaze);
    ArrayList<AState> solutionPath = solution.getSolutionPath();

    @Test
    void name() {
    }
}