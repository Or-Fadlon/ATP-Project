package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    static ArrayList<Position> getNeighbourWalls(Maze maze, Position currentPosition) {
        ArrayList<Position> wallsList = new ArrayList<>();
        int columnIndex = currentPosition.getColumnIndex(), rowIndex = currentPosition.getRowIndex();
        if (rowIndex + 1 < maze.getRowsSize() - 1 && maze.positionOfWall(rowIndex + 1, columnIndex))
            wallsList.add(new Position(rowIndex + 1, columnIndex));
        if (columnIndex + 1 < maze.getColumnsSize() - 1 && maze.positionOfWall(rowIndex, columnIndex + 1))
            wallsList.add(new Position(rowIndex, columnIndex + 1));
        if (rowIndex - 1 >= 1 && maze.positionOfWall(rowIndex - 1, columnIndex))
            wallsList.add(new Position(rowIndex - 1, columnIndex));
        if (columnIndex - 1 >= 1 && maze.positionOfWall(rowIndex, columnIndex - 1))
            wallsList.add(new Position(rowIndex, columnIndex - 1));
        return wallsList;
    }

    private static ArrayList<Position> getNeighbourTiles(Maze maze, Position currentPosition) {
        ArrayList<Position> tilesList = new ArrayList<>();
        int columnIndex = currentPosition.getColumnIndex(), rowIndex = currentPosition.getRowIndex();
        if (rowIndex + 1 < maze.getRowsSize() && maze.positionOfTile(rowIndex + 1, columnIndex))
            tilesList.add(new Position(rowIndex + 1, columnIndex));
        if (columnIndex + 1 < maze.getColumnsSize() && maze.positionOfTile(rowIndex, columnIndex + 1))
            tilesList.add(new Position(rowIndex, columnIndex + 1));
        if (rowIndex - 1 >= 0 && maze.positionOfTile(rowIndex - 1, columnIndex))
            tilesList.add(new Position(rowIndex - 1, columnIndex));
        if (columnIndex - 1 >= 0 && maze.positionOfTile(rowIndex, columnIndex - 1))
            tilesList.add(new Position(rowIndex, columnIndex - 1));
        return tilesList;
    }

    private static void connectNeighbours(Maze maze, Position currentPosition, Position neighbour) {
        if (currentPosition.getColumnIndex() == neighbour.getColumnIndex()) {
            maze.removeWall(new Position(Math.min(neighbour.getRowIndex(), currentPosition.getRowIndex()) + 1, currentPosition.getColumnIndex()));
        } else if (currentPosition.getRowIndex() == neighbour.getRowIndex()) {
            maze.removeWall(new Position(currentPosition.getRowIndex(), Math.min(neighbour.getColumnIndex(), currentPosition.getColumnIndex()) + 1));
        }
    }

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
    public Maze generate(int rows, int columns) {
        Random random = new Random();
        Maze maze = new Maze(rows, columns);
        maze.makeAllWalls(); //1
        ArrayList<Position> wallsList = new ArrayList<>();

        Position currentPosition = maze.generateStartPosition();
        if (currentPosition == null)
            throw new RuntimeException("Expected for startPosition but null was return");
        maze.removeWall(maze.getStartPosition());
        maze.removeWall(currentPosition);
        wallsList.addAll(getNeighbourWalls(maze, currentPosition)); //2
        while (!wallsList.isEmpty()) { //3
            currentPosition = wallsList.remove(random.nextInt(wallsList.size()));
            ArrayList<Position> neighbourTiles = getNeighbourTiles(maze, currentPosition);
            if (neighbourTiles.size() == 1) { //3.1
                Position neighbour = neighbourTiles.get(random.nextInt(neighbourTiles.size()));
                maze.removeWall(currentPosition);
                connectNeighbours(maze, currentPosition, neighbour);
                wallsList.addAll(getNeighbourWalls(maze, currentPosition));
            }
        }

        maze.generateGoalPosition();
        maze.removeWall(maze.getGoalPosition());
        return maze;
    }
}
