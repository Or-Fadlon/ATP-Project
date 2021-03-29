package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;

public class Maze3D {
    private static final int WALL = 1, TILE = 0;
    int[][][] grid;
    Position3D startPosition, goalPosition;

    public Maze3D(int depth, int rows, int columns) {
        if (depth < 2 || columns < 2 || rows < 2)
            throw new IllegalArgumentException("one or more of the arguments are < 2");
        this.grid = new int[depth][rows][columns];
        this.startPosition = new Position3D(0, 0, 0);
        this.goalPosition = new Position3D(depth - 1, rows - 1, columns - 1);
    }

    public Maze3D(int[][][] grid, Position3D startPosition, Position3D goalPosition) {
        this.grid = grid;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
    }

    public int[][][] getMap() {
        return grid;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }

    public void print() {
        final String RED = "\033[0;31m"; //REMOVE
        final String GREEN = "\033[0;32m"; //REMOVE
        final String RESET = "\033[0m"; //REMOVE
        System.out.println("{");
        for (int i = 0; i < this.getDepthSize(); i++) {
            for (int j = 0; j < this.getRowsSize(); j++) {
                System.out.print("{");
                for (int k = 0; k < this.getColumnsSize(); k++) {
                    if (this.startPosition.equals(new Position3D(i, j, k)))
                        System.out.print(GREEN + " S" + RESET);
                    else if (this.goalPosition.equals(new Position3D(i, j, k)))
                        System.out.print(RED + " E" + RESET);
                    else
                        System.out.print(" " + (this.grid[i][j][k] == 1 ? "B" : " "));
                }
                System.out.println(" }");
            }
            if (this.getDepthSize() - 1 != i) {
                for (int t = 0; t < this.getColumnsSize() * 2 + 3; t++)
                    System.out.print("-");
                System.out.println();
            } else
                System.out.println("}");
        }
        if (this.positionOfWall(this.startPosition) || this.positionOfWall(this.goalPosition)) //REMOVE
            System.out.println("*****HOOOOO NOOOO******"); //REMOVE
    }


    public void cleanAllWalls() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                for (int k = 0; k < grid[0].length; k++)
                    this.grid[i][j][k] = TILE;
    }

    public void makeAllWalls() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                for (int k = 0; k < grid[0].length; k++)
                    this.grid[i][j][k] = WALL;
    }

    public void addWall(Position3D position) {
        this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] = WALL;
    }

    public void removeWall(Position3D position) {
        this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] = TILE;
    }

    public int getDepthSize() {
        return this.grid.length;
    }

    public int getRowsSize() {
        return this.grid[0].length;
    }

    public int getColumnsSize() {
        return this.grid[0][0].length;

    }

    //TODO: code duplication remove!!!
    public ArrayList<Position3D> getNeighbourWalls(Position3D currentPosition) {
        ArrayList<Position3D> wallsList = new ArrayList<>();
        if (this.validMazePosition(currentPosition)) {
            if (this.validMazePosition(currentPosition.getUpPosition()) && this.positionOfWall(currentPosition.getUpPosition()))
                wallsList.add(currentPosition.getUpPosition());
            if (this.validMazePosition(currentPosition.getDownPosition()) && this.positionOfWall(currentPosition.getDownPosition()))
                wallsList.add(currentPosition.getDownPosition());
            if (this.validMazePosition(currentPosition.getLeftPosition()) && this.positionOfWall(currentPosition.getLeftPosition()))
                wallsList.add(currentPosition.getLeftPosition());
            if (this.validMazePosition(currentPosition.getRightPosition()) && this.positionOfWall(currentPosition.getRightPosition()))
                wallsList.add(currentPosition.getRightPosition());
            if (this.validMazePosition(currentPosition.getHigherPosition()) && this.positionOfWall(currentPosition.getHigherPosition()))
                wallsList.add(currentPosition.getHigherPosition());
            if (this.validMazePosition(currentPosition.getLowerPosition()) && this.positionOfWall(currentPosition.getLowerPosition()))
                wallsList.add(currentPosition.getLowerPosition());
        }
        return wallsList;
    }

    //TODO: code duplication remove!!!
    public ArrayList<Position3D> getNeighbourTiles(Position3D currentPosition) {
        ArrayList<Position3D> tilesList = new ArrayList<>();
        if (this.validMazePosition(currentPosition)) {
            if (this.validMazePosition(currentPosition.getUpPosition()) && this.positionOfTile(currentPosition.getUpPosition()))
                tilesList.add(currentPosition.getUpPosition());
            if (this.validMazePosition(currentPosition.getDownPosition()) && this.positionOfTile(currentPosition.getDownPosition()))
                tilesList.add(currentPosition.getDownPosition());
            if (this.validMazePosition(currentPosition.getLeftPosition()) && this.positionOfTile(currentPosition.getLeftPosition()))
                tilesList.add(currentPosition.getLeftPosition());
            if (this.validMazePosition(currentPosition.getRightPosition()) && this.positionOfTile(currentPosition.getRightPosition()))
                tilesList.add(currentPosition.getRightPosition());
            if (this.validMazePosition(currentPosition.getHigherPosition()) && this.positionOfTile(currentPosition.getHigherPosition()))
                tilesList.add(currentPosition.getHigherPosition());
            if (this.validMazePosition(currentPosition.getLowerPosition()) && this.positionOfTile(currentPosition.getLowerPosition()))
                tilesList.add(currentPosition.getLowerPosition());
        }
        return tilesList;
    }

    public void connectNeighbours(Position3D currentPosition, Position3D neighbour) {
        if (currentPosition.getDepthIndex() == neighbour.getDepthIndex()) {
            if (currentPosition.getRowIndex() == neighbour.getRowIndex()) {
                this.removeWall(new Position3D(currentPosition.getDepthIndex(), currentPosition.getRowIndex(), Math.min(neighbour.getColumnIndex(), currentPosition.getColumnIndex()) + 1));
            } else if (currentPosition.getColumnIndex() == neighbour.getColumnIndex()) {
                this.removeWall(new Position3D(currentPosition.getDepthIndex(), Math.min(neighbour.getRowIndex(), currentPosition.getRowIndex()) + 1, currentPosition.getColumnIndex()));
            }
        } else if (currentPosition.getRowIndex() == neighbour.getRowIndex()) {
            if (currentPosition.getDepthIndex() == neighbour.getDepthIndex()) {
                this.removeWall(new Position3D(currentPosition.getDepthIndex(), currentPosition.getRowIndex(), Math.min(neighbour.getColumnIndex(), currentPosition.getColumnIndex()) + 1));
            } else if (currentPosition.getColumnIndex() == neighbour.getColumnIndex()) {
                this.removeWall(new Position3D(Math.min(currentPosition.getDepthIndex(), neighbour.getDepthIndex()) + 1, currentPosition.getRowIndex(), currentPosition.getColumnIndex()));
            }
        } else if (currentPosition.getColumnIndex() == neighbour.getColumnIndex()) {
            if (currentPosition.getDepthIndex() == neighbour.getDepthIndex()) {
                this.removeWall(new Position3D(currentPosition.getDepthIndex(), Math.min(currentPosition.getRowIndex(), neighbour.getRowIndex()) + 1, Math.min(neighbour.getColumnIndex(), currentPosition.getColumnIndex()) + 1));
            } else if (currentPosition.getRowIndex() == neighbour.getRowIndex()) {
                this.removeWall(new Position3D(Math.min(currentPosition.getDepthIndex(), neighbour.getDepthIndex()) + 1, currentPosition.getRowIndex(), currentPosition.getColumnIndex()));
            }
        }
    }

    public void generateStartPosition() {
        Random random = new Random();
        int side = random.nextInt(6);
        switch (side) {
            case 0 -> this.startPosition = new Position3D(0, random.nextInt(this.getRowsSize()), random.nextInt(this.getColumnsSize())); // UP
            case 1 -> this.startPosition = new Position3D(this.getDepthSize() - 1, random.nextInt(this.getRowsSize()), random.nextInt(this.getColumnsSize())); // DOWN
            case 2 -> this.startPosition = new Position3D(random.nextInt(this.getDepthSize()), random.nextInt(this.getRowsSize()), this.getColumnsSize() - 1); // RIGHT
            case 3 -> this.startPosition = new Position3D(random.nextInt(this.getDepthSize()), random.nextInt(this.getRowsSize()), 0); // LEFT
            case 4 -> this.startPosition = new Position3D(random.nextInt(this.getDepthSize()), this.getRowsSize() - 1, random.nextInt(this.getColumnsSize())); // FRONT
            case 5 -> this.startPosition = new Position3D(random.nextInt(this.getDepthSize()), 0, random.nextInt(this.getColumnsSize())); // BACK
            default -> this.startPosition = new Position3D(0, 0, 0);
        }
    }

    public void generateGoalPosition() {
        Random random = new Random();
        int side = random.nextInt(6);
        do {
            switch (side) {
                case 0 -> this.goalPosition = new Position3D(0, random.nextInt(this.getRowsSize()), random.nextInt(this.getColumnsSize())); // UP
                case 1 -> this.goalPosition = new Position3D(this.getDepthSize() - 1, random.nextInt(this.getRowsSize()), random.nextInt(this.getColumnsSize())); // DOWN
                case 2 -> this.goalPosition = new Position3D(random.nextInt(this.getDepthSize()), random.nextInt(this.getRowsSize()), this.getColumnsSize() - 1); // RIGHT
                case 3 -> this.goalPosition = new Position3D(random.nextInt(this.getDepthSize()), random.nextInt(this.getRowsSize()), 0); // LEFT
                case 4 -> this.goalPosition = new Position3D(random.nextInt(this.getDepthSize()), this.getRowsSize() - 1, random.nextInt(this.getColumnsSize())); // FRONT
                case 5 -> this.goalPosition = new Position3D(random.nextInt(this.getDepthSize()), 0, random.nextInt(this.getColumnsSize())); // BACK
                default -> this.goalPosition = new Position3D(0, 0, 0);
            }
        }
        while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition()));
    }

    public boolean positionOfWall(Position3D position) {
        return this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] == WALL;
    }

    public boolean positionOfTile(Position3D position) {
        return this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] == TILE;
    }

    public boolean validMazePosition(Position3D position) {
        return (0 <= position.getDepthIndex() && position.getDepthIndex() < this.getDepthSize() &&
                0 <= position.getRowIndex() && position.getRowIndex() < this.getRowsSize() &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.getColumnsSize());
    }
}
