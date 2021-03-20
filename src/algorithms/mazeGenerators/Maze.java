package algorithms.mazeGenerators;

public class Maze {
    protected Position startPosition, goalPosition;
    protected int[][] grid;


    public Maze(int rows, int columns) {
        this.grid = new int[rows][columns];
        this.startPosition = new Position(0,0);
        this.goalPosition = new Position(0,0);
    }

    public void print(){

    }

    /**
     *
     * @return the start Position of the maze
     */
    public Position getStartPosition() {
        return this.startPosition;
    }

    /**
     *
     * @return the goal Position of the maze
     */
    public Position getGoalPosition() {
        return this.goalPosition;
    }
}
