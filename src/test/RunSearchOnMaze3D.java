package test;

import algorithms.maze3D.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
    IMazeGenerator3D mg = new MyMaze3DGenerator();
    Maze3D maze = mg.generate(3,3, 5);
//        int[][][] map = {
//                {
//                        {0, 0, 1, 0, 1},
//                        {0, 1, 1, 1, 0},
//                        {0, 1, 1, 0, 0}},
//                {
//                        {1, 1, 1, 0, 1},
//                        {1, 0, 0, 1, 0},
//                        {0, 0, 1, 0, 1}},
//                {
//                        {1, 1, 1, 0, 1},
//                        {1, 1, 0, 0, 0},
//                        {1, 1, 1, 0, 1}}
//        };
//        Maze3D maze = new Maze3D(map, new Position3D(0,0,0), new Position3D(0,2,4));

        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
    maze.print();

    solveProblem(searchableMaze, new BreadthFirstSearch());
    solveProblem(searchableMaze, new DepthFirstSearch());
    solveProblem(searchableMaze, new BestFirstSearch());
}

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s", i, solutionPath.get(i)));
        }
    }
}
