package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    MyMazeGenerator mg = new MyMazeGenerator();
    Maze maze = mg.generate(100, 100);
    SearchableMaze searchableMaze = new SearchableMaze(maze);
    BestFirstSearch searcher = new BestFirstSearch();
    Solution solution = searcher.solve(searchableMaze);
    ArrayList<AState> solutionPath = solution.getSolutionPath();

    BestFirstSearch bestFirstSearch = new BestFirstSearch();

    @Test
    void searchName() {
        assertEquals(bestFirstSearch.getName(), "Best First Search");
    }

    @Test
    void solutionStartAndGoalPosition() {
        assertEquals(maze.getStartPosition(), solutionPath.get(0).getCurrentState());
        assertEquals(maze.getGoalPosition(), solutionPath.get(solutionPath.size() - 1).getCurrentState());
    }

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

    @Test
    void cheaperThenBFS() {
        BreadthFirstSearch BFS = new BestFirstSearch();
        Solution solutionBFS = BFS.solve(searchableMaze);
        assertTrue(solution.getCost() <= solutionBFS.getCost());
    }
}