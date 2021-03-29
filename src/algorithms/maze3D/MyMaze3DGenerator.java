package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    /***
     * 1. Start with a grid full of walls.
     * 2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
     * 3. While there are walls in the list:
     *      3.1. Pick a random wall from the list.
     *      If only one of the two cells that the wall divides is visited, then:
     *          3.1.1. Make the wall a passage and mark the unvisited cell as part of the maze.
     *          3.1.2. Add the neighboring walls of the cell to the wall list.
     *      3.2. Remove the wall from the list.
     * @param rows number of rows of the maze to generate
     * @param columns number of columns of the maze to generate
     * @return generated maze
     */
    @Override
    public Maze3D generate(int depth, int rows, int columns) {
        Random random = new Random();
        Maze3D maze = new Maze3D(depth, rows, columns);
        maze.makeAllWalls(); //1
        ArrayList<Position3D> wallsList = new ArrayList<>();
        maze.generateStartPosition();
        Position3D currentPosition = maze.getStartPosition();
        maze.removeWall(maze.getStartPosition());
        maze.removeWall(currentPosition);
        wallsList.addAll(maze.getNeighbourWalls(currentPosition)); //2
        while (!wallsList.isEmpty()) { //3
            currentPosition = wallsList.remove(random.nextInt(wallsList.size()));
            ArrayList<Position3D> neighbourTiles = maze.getNeighbourTiles(currentPosition);
            if (neighbourTiles.size() == 1) { //3.1
                Position3D neighbour = neighbourTiles.get(random.nextInt(neighbourTiles.size()));
                maze.removeWall(currentPosition);
                maze.connectNeighbours(currentPosition, neighbour);
                wallsList.addAll(maze.getNeighbourWalls(currentPosition));
            }
        }

        maze.generateGoalPosition();
        return maze;
    }


}
