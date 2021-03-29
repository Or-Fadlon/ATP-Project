package test;

import algorithms.maze3D.*;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator) {
        final int depth = 3, rows = 3, columns = 5;
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generationtime(ms): %s ", mazeGenerator.measureAlgorithmTimeMillis(depth, rows, columns)));
        // generate another maze
//        Maze3D maze = mazeGenerator.generate(depth, rows, columns);
        int[][][] map = {
                {
                        {0, 0, 1, 0, 1},
                        {0, 1, 1, 1, 0},
                        {0, 1, 1, 0, 0}},
                {
                        {1, 1, 1, 0, 1},
                        {1, 0, 0, 1, 0},
                        {0, 0, 1, 0, 1}},
                {
                        {1, 1, 1, 0, 1},
                        {1, 1, 0, 0, 0},
                        {1, 1, 1, 0, 1}}
        };
        Maze3D maze = new Maze3D(map, new Position3D(0,0,0), new Position3D(0,2,4));
        // prints the maze
        maze.print();
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
