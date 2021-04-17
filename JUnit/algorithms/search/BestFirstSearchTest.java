package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    MyMazeGenerator mg = new MyMazeGenerator();
    Maze maze = new Maze(10, 10);
    SearchableMaze searchableMaze = new SearchableMaze(maze);
    BestFirstSearch searcher = new BestFirstSearch();
    Solution solution = searcher.solve(searchableMaze);
    ArrayList<AState> solutionPath = solution.getSolutionPath();

    BestFirstSearch bestFirstSearch = new BestFirstSearch();

    @Test
    void solutionStartGoalPosition() {
        assertEquals(maze.getStartPosition(), solutionPath.get(0).getCurrentState());
        assertEquals(maze.getGoalPosition(), solutionPath.get(solutionPath.size() - 1).getCurrentState());
    }

//    @Test
//    void validPath() {
//        boolean flag = true;
//        AState current, prev;
//        for (int i = 1; i < solutionPath.size(); i++) {
//            flag = true;
//            prev = solutionPath.get(i - 1);
//            current = solutionPath.get(i);
//            if (prev.equals(current))
//                flag = false;
//
//            assertTrue(flag);
//        }
//    }

    @Test
    void nullCheck() {
        boolean flag = false;
        try {
            bestFirstSearch.solve(null);
        } catch (IllegalArgumentException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    void noSolution() {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        Position startPosition = new Position(0, 0);
        Position goalPosition = new Position(grid.length - 1, grid[0].length - 1);
        Maze maze = new Maze(grid, startPosition, goalPosition);
        Solution solution = bestFirstSearch.solve(new SearchableMaze(maze));
        assertEquals(solution.getSolutionPath().size(), 0);
    }

}