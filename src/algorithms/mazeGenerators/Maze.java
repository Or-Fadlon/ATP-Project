package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {
    private static final int WALL = 1, TILE = 0;
    protected Position startPosition, goalPosition;
    protected int[][] grid;


    public Maze(int rows, int columns) {
        if (columns < 3 || rows < 3)
            throw new IllegalArgumentException("one or more of the arguments are < 3");
        this.grid = new int[rows][columns];
        this.startPosition = new Position(0, 0);
        this.goalPosition = new Position(columns - 1, rows - 1);
    }

    public void print() {
        for (int[] arr : this.grid) {
            for (int i : arr) {
                System.out.print("" + i + " ");
            }
            System.out.println();
        }
    }

    /**
     * @return the start Position of the maze
     */
    public Position getStartPosition() {
        return new Position(this.startPosition);
    }

    public void setStartPosition(Position position) {
        this.startPosition = new Position(position);
    }

    /**
     * @return the goal Position of the maze
     */
    public Position getGoalPosition() {
        return new Position(this.goalPosition);
    }

    public void setGoalPosition(Position position) {
        this.goalPosition = new Position(position);
    }

    public boolean positionOfWall(int row, int column) {
        return this.grid[row][column] == WALL;
    }

    public boolean positionOfTile(int row, int column) {
        return this.grid[row][column] == TILE;
    }

    public void cleanAllWalls() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                this.grid[i][j] = TILE;
    }

    public void makeAllWalls() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                this.grid[i][j] = WALL;
    }

    public boolean addWall(int row, int column) {
        if (this.grid[row][column] == TILE) {
            this.grid[row][column] = WALL;
            return true;
        }
        return false;
    }

    public boolean removeWall(int row, int column) {
        if (this.grid[row][column] == WALL) {
            this.grid[row][column] = TILE;
            return true;
        }
        return false;
    }
}
