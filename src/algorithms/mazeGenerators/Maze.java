package algorithms.mazeGenerators;

public class Maze {
    private static final int WALL = 1, TILE = 0;
    private Position startPosition, goalPosition;
    private int[][] grid;


    public Maze(int rows, int columns) {
        if (columns < 2 || rows < 2)
            throw new IllegalArgumentException("one or more of the arguments are < 2");
        this.grid = new int[rows][columns];
        this.startPosition = new Position(0, 1);
        this.goalPosition = new Position(rows - 1, columns - 2);
    }

    public void print() {
        for (int[] arr : this.grid) {
            for (int i : arr) {
                System.out.print("" + (i == 1 ? "█" : " ") + " ");
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
}
