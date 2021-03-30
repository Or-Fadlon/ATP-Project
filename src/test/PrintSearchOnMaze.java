package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.HashSet;

public class PrintSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        maze.printColored();
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.printf("'%s' algorithm - nodes evaluated:%s%n", searcher.getName(), searcher.getNumberOfNodesEvaluated());
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();

        HashSet<String> trace = new HashSet<>();
        for (int i = 0; i < solutionPath.size(); i++)
            trace.add(solutionPath.get(i).toString());
        ((SearchableMaze) domain).maze.printColoredTrace(trace);

        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.printf("%s.%s%n", i, solutionPath.get(i));
        }
        System.out.printf("%s = %s%n", searcher.getName(), solution.getCost());
//        System.out.println(String.format("%s.%s", 0, solutionPath.get(0)));
//        System.out.println(String.format("%s.%s", solutionPath.size() - 1, solutionPath.get(solutionPath.size() - 1)));

    }
}