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

    public void print() {
        for (int i = 0; i < this.getRowsSize(); i++) {
            System.out.print("{");
            for (int j = 0; j < this.getColumnsSize(); j++) {
                if (this.startPosition.equals(new Position(i, j)))
                    System.out.print(" S");
                else if (this.goalPosition.equals(new Position(i, j)))
                    System.out.print(" E");
                else
                    System.out.print(" " + this.grid[i][j]);
            }
            System.out.println(" }");
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

    public Position generateStartPosition() {
        Random random = new Random();
        int side = random.nextInt(4);
        switch (side) {
            case 0:
                this.setStartPosition(new Position(0, random.nextInt(this.getColumnsSize() - 2) + 1));
                return new Position(this.getStartPosition().getRowIndex() + 1, this.getStartPosition().getColumnIndex());
            case 1:
                this.setStartPosition(new Position(random.nextInt(this.getRowsSize() - 2) + 1, this.getColumnsSize() - 1));
                return new Position(this.getStartPosition().getRowIndex(), this.getStartPosition().getColumnIndex() - 1);
            case 2:
                this.setStartPosition(new Position(this.getRowsSize() - 1, random.nextInt(this.getColumnsSize() - 2) + 1));
                return new Position(this.getStartPosition().getRowIndex() - 1, this.getStartPosition().getColumnIndex());
            case 3:
                this.setStartPosition(new Position(random.nextInt(this.getRowsSize() - 2) + 1, 0));
                return new Position(this.getStartPosition().getRowIndex(), this.getStartPosition().getColumnIndex() + 1);
            default:
                return null;
        }
    }

    public void generateGoalPosition() {
        Random random = new Random();
        int side = random.nextInt(4);
        switch (side) {
            case 0:
                do
                    this.setGoalPosition(new Position(0, random.nextInt(this.getColumnsSize() - 2) + 1));
                while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition().getRowIndex() + 1, this.getGoalPosition().getColumnIndex()));
                break;
            case 1:
                do
                    this.setGoalPosition(new Position(random.nextInt(this.getRowsSize() - 2) + 1, this.getColumnsSize() - 1));
                while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition().getRowIndex(), this.getGoalPosition().getColumnIndex() - 1));
                break;
            case 2:
                do
                    this.setGoalPosition(new Position(this.getRowsSize() - 1, random.nextInt(this.getColumnsSize() - 2) + 1));
                while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition().getRowIndex() - 1, this.getGoalPosition().getColumnIndex()));
                break;
            case 3:
                do
                    this.setGoalPosition(new Position(random.nextInt(this.getRowsSize() - 2) + 1, 0));
                while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition().getRowIndex(), this.getGoalPosition().getColumnIndex() + 1));
                break;
        }
    }
}
