package algorithms.maze3D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator {


    /**
     * maze generator using one of the knowing algorithms:
     *
     * @param rows    number of rows of the maze to generate
     * @param columns number of columns of the maze to generate
     * @return generated maze
     */
    @Override
    public Maze3D generate(int depth, int rows, int columns) {
        if (depth == 2 || rows == 2 || columns == 2)
            return primsMazeGenerator(depth, rows, columns);
        return DFSMazeGenerator(depth, rows, columns);
    }

    /**
     * maze generator using Prims algorithm:
     * <p>
     * 1. Start with a grid full of walls.
     * 2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
     * 3. While there are walls in the list:
     * 3.1.     Pick a random wall from the list.
     * 3.2.     If only one of the two cells that the wall divides is visited, then:
     * 3.2.1.       Make the wall a passage and mark the unvisited cell as part of the maze.
     * 3.2.2.       Add the neighboring walls of the cell to the wall list.
     * 3.3.     Remove the wall from the list.
     *
     * @param depth   number of layers of the maze to generate
     * @param rows    number of rows of the maze to generate
     * @param columns number of columns of the maze to generate
     * @return generated maze
     */
    private Maze3D primsMazeGenerator(int depth, int rows, int columns) {
        Random random = new Random();
        Maze3D maze = new Maze3D(depth, rows, columns);
        HashSet<Position3D> visited = new HashSet<>();
        ArrayList<Position3D> wallsList;
        ArrayList<Position3D> neighbourTiles;
        maze.makeAllWalls();
        maze.generateStartPosition();
        Position3D currentPosition = maze.getStartPosition();
        maze.removeWall(currentPosition);
        wallsList = new ArrayList<>(maze.getNeighbourWalls(currentPosition));
        visited.addAll(maze.getNeighbourWalls(currentPosition));
        visited.add(currentPosition);
        while (!wallsList.isEmpty()) {
            currentPosition = wallsList.remove(random.nextInt(wallsList.size()));
            neighbourTiles = maze.getNeighbourTiles(currentPosition);
            if (neighbourTiles.size() == 1) {
                maze.removeWall(currentPosition);
//                maze.connectNeighbours(currentPosition, neighbourTiles.get(0));
                for (Position3D wall : maze.getNeighbourWalls(currentPosition))
                    if (!visited.contains(wall)) {
                        wallsList.add(wall);
                        visited.add(wall);
                    }
            }
        }

        maze.generateGoalPosition();
        return maze;
    }

    /**
     * maze generator using iterative DFS algorithm:
     * <p>
     * 1. Choose the initial cell, mark it as visited and push it to the stack
     * 2. While the stack is not empty
     * 2.1.     Pop a cell from the stack and make it a current cell
     * 2.2.     If the current cell has any neighbours which have not been visited
     * 2.2.1        Push the current cell to the stack
     * 2.2.2        Choose one of the unvisited neighbours
     * 2.2.3        Remove the wall between the current cell and the chosen cell
     * 2.2.4        Mark the chosen cell as visited and push it to the stack
     *
     * @param depth   number of layers of the maze to generate
     * @param rows    number of rows of the maze to generate
     * @param columns number of columns of the maze to generate
     * @return generated maze
     */
    private Maze3D DFSMazeGenerator(int depth, int rows, int columns) {
        Random random = new Random();
        Maze3D maze = new Maze3D(depth, rows, columns);
        HashSet<Position3D> visited = new HashSet<>();
        Stack<Position3D> neighbours = new Stack<>();
        ArrayList<Position3D> neighbourWalls;
        maze.makeAllWalls();
        maze.generateStartPosition();
        Position3D currentPosition = maze.getStartPosition();
        maze.removeWall(currentPosition);
        neighbours.push(currentPosition);
        visited.add(currentPosition);
        while (!neighbours.isEmpty()) {
            currentPosition = neighbours.pop();
            neighbourWalls = maze.wallsTwoBlocksAway(currentPosition, visited);
            if (neighbourWalls.size() != 0) {
                neighbours.push(currentPosition);
                Position3D randNeighbour = neighbourWalls.get(random.nextInt(neighbourWalls.size()));
                maze.removeWall(randNeighbour);
                maze.connectNeighbours(currentPosition, randNeighbour);
                visited.add(randNeighbour);
                neighbours.push(randNeighbour);
            }
        }

        maze.generateGoalPosition();
        return maze;
    }

}
