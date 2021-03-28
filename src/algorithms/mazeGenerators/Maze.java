package algorithms.mazeGenerators;

import java.util.Random;

public class Maze {
    private static final int WALL = 1, TILE = 0;
    private Position startPosition, goalPosition;
    private int[][] grid;


    public Maze(int rows, int columns) {
        if (columns < 2 || rows < 2)
            throw new IllegalArgumentException("one or more of the arguments are < 2");
        this.grid = new int[rows][columns];
        this.startPosition = new Position(0, 0);
        this.goalPosition = new Position(rows - 1, columns - 1);
    }

    //TODO: remove colors!!
    public void print() {
        final String RED = "\033[0;31m"; //REMOVE
        final String GREEN = "\033[0;32m"; //REMOVE
        final String RESET = "\033[0m"; //REMOVE
        for (int i = 0; i < this.getRowsSize(); i++) {
            System.out.print("{");
            for (int j = 0; j < this.getColumnsSize(); j++) {
                if (this.startPosition.equals(new Position(i, j)))
                    System.out.print(GREEN + " S" + RESET);
                else if (this.goalPosition.equals(new Position(i, j)))
                    System.out.print(RED + " E" + RESET);
                else
                    System.out.print(" " + (this.grid[i][j] == 1 ? "B" : " "));
            }
            System.out.println(" }");
        }
        if (this.positionOfWall(this.startPosition) || this.positionOfWall(this.goalPosition)) //REMOVE
            System.out.println("*****HOOOOO NOOOO******"); //REMOVE
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

    public boolean positionOfWall(Position position) {
        return this.grid[position.getRowIndex()][position.getColumnIndex()] == WALL;
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

    public boolean addWall(Position position) {
        if (this.grid[position.getRowIndex()][position.getColumnIndex()] == TILE) {
            this.grid[position.getRowIndex()][position.getColumnIndex()] = WALL;
            return true;
        }
        return false;
    }

    public boolean removeWall(Position position) {
        if (this.grid[position.getRowIndex()][position.getColumnIndex()] == WALL) {
            this.grid[position.getRowIndex()][position.getColumnIndex()] = TILE;
            return true;
        }
        return false;
    }

    public int getRowsSize() {
        return grid.length;
    }

    public int getColumnsSize() {
        return grid[0].length;
    }

    public boolean validMazePosition(Position position) {
        return (0 <= position.getRowIndex() && position.getRowIndex() < this.getRowsSize() &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.getColumnsSize());
    }

    public void generateStartPosition() {
        Random random = new Random();
        int side = random.nextInt(4);
        switch (side) {
            case 0 -> this.setStartPosition(new Position(0, random.nextInt(this.getColumnsSize())));
            case 1 -> this.setStartPosition(new Position(random.nextInt(this.getRowsSize()), this.getColumnsSize() - 1));
            case 2 -> this.setStartPosition(new Position(this.getRowsSize() - 1, random.nextInt(this.getColumnsSize())));
            case 3 -> this.setStartPosition(new Position(random.nextInt(this.getRowsSize()), 0));
            default -> this.setStartPosition(new Position(0, 0));
        }
    }

    public void generateGoalPosition() {
        Random random = new Random();
        int side = random.nextInt(4);
        do {
            switch (side) {
                case 0 -> this.setGoalPosition(new Position(0, random.nextInt(this.getColumnsSize())));
                case 1 -> this.setGoalPosition(new Position(random.nextInt(this.getRowsSize()), this.getColumnsSize() - 1));
                case 2 -> this.setGoalPosition(new Position(this.getRowsSize() - 1, random.nextInt(this.getColumnsSize())));
                case 3 -> this.setGoalPosition(new Position(random.nextInt(this.getRowsSize()), 0));
            }
        }
        while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition()));
    }
}
