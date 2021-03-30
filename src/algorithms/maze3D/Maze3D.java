package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represent a 3D maze
 */
public class Maze3D {
    private static final int WALL = 1, TILE = 0;
    private int[][][] grid;
    private Position3D startPosition, goalPosition;

    /**
     * constructor
     *
     * @param depth   number of layers
     * @param rows    number of rows
     * @param columns number of columns
     * @throws IllegalArgumentException if one one or more of the arguments are < 2
     */
    public Maze3D(int depth, int rows, int columns) {
        if (depth < 2 || columns < 2 || rows < 2)
            throw new IllegalArgumentException("one or more of the arguments are < 2");
        this.grid = new int[depth][rows][columns];
        this.startPosition = new Position3D(0, 0, 0);
        this.goalPosition = new Position3D(depth - 1, rows - 1, columns - 1);
    }

    /**
     * constructor
     *
     * @param grid          a grid full of WALLs and TILEs
     * @param startPosition maze start position
     * @param goalPosition  maze goal position
     */
    public Maze3D(int[][][] grid, Position3D startPosition, Position3D goalPosition) {
        this.grid = grid;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
    }

    /**
     * @return maze start position
     */
    public Position3D getStartPosition() {
        return startPosition;
    }

    /**
     * @return maze goal position
     */
    public Position3D getGoalPosition() {
        return goalPosition;
    }

    /**
     * print a colored console view of the maze
     */
    public void printColored() {
        final String RED = "\033[0;31m";
        final String GREEN = "\033[0;32m";
        final String RESET = "\033[0m";
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
    }

    /**
     * print a console view of the maze
     */
    public void print() {
        System.out.println("{");
        for (int i = 0; i < this.getDepthSize(); i++) {
            for (int j = 0; j < this.getRowsSize(); j++) {
                System.out.print("{");
                for (int k = 0; k < this.getColumnsSize(); k++) {
                    if (this.startPosition.equals(new Position3D(i, j, k)))
                        System.out.print(" S");
                    else if (this.goalPosition.equals(new Position3D(i, j, k)))
                        System.out.print(" E");
                    else
                        System.out.print(" " + this.grid[i][j][k]);
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
    }


    /**
     * remove all walls from the maze
     */
    public void cleanAllWalls() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                for (int k = 0; k < grid[0].length; k++)
                    this.grid[i][j][k] = TILE;
    }

    /**
     * add a wall in every tile of the maze
     */
    public void makeAllWalls() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                for (int k = 0; k < grid[0].length; k++)
                    this.grid[i][j][k] = WALL;
    }

    /**
     * add a wall to the given position in the maze.
     * do nothing if the position isn't a valid position in the maze
     *
     * @param position a valid position in the maze
     */
    public void addWall(Position3D position) {
        if (this.validMazePosition(position))
            this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] = WALL;
    }

    /**
     * remove a wall from the given position in the maze.
     * do nothing if the position isn't a valid position in the maze
     *
     * @param position a valid position in the maze
     */
    public void removeWall(Position3D position) {
        if (this.validMazePosition(position))
            this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] = TILE;
    }

    /**
     * @return number of layers in the maze
     */
    public int getDepthSize() {
        return this.grid.length;
    }

    /**
     * @return number of rows in the maze
     */
    public int getRowsSize() {
        return this.grid[0].length;
    }

    /**
     * @return number of columns in the maze
     */
    public int getColumnsSize() {
        return this.grid[0][0].length;

    }

    /**
     * get all the neighbour WALLs position around given position
     *
     * @param currentPosition position to get the surrounding positions
     * @return all the surrounding WALLs positions of the given position
     * @throws IllegalArgumentException one of the given positions is not a valid position in the maze
     */
    public ArrayList<Position3D> getNeighbourWalls(Position3D currentPosition) {
        ArrayList<Position3D> wallsList = new ArrayList<>();
        if (!this.validMazePosition(currentPosition)) {
            throw new IllegalArgumentException("one of the given positions is not a valid position in the maze");
        }
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
        return wallsList;
    }

    /**
     * get all the neighbour TILEs position around given position
     *
     * @param currentPosition position to get the surrounding positions
     * @return all the surrounding TILEs positions of the given position
     * @throws IllegalArgumentException one of the given positions is not a valid position in the maze
     */
    public ArrayList<Position3D> getNeighbourTiles(Position3D currentPosition) {
        ArrayList<Position3D> tilesList = new ArrayList<>();
        if (!this.validMazePosition(currentPosition)) {
            throw new IllegalArgumentException("one of the given positions is not a valid position in the maze");
        }
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
        return tilesList;
    }

    /**
     * connect between two positions that have one block separate in the middle between them.
     * X?Y - ? for wall to remove
     *
     * @param currentPosition position of one - X
     * @param neighbour       position of one block away neighbour - Y
     */
    public void connectNeighbours(Position3D currentPosition, Position3D neighbour) {
        if (!validMazePosition(currentPosition) || !validMazePosition(neighbour))
            throw new IllegalArgumentException("one of the given positions is not a valid position in the maze");
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

    /**
     * randomly select a new starting position for the maze
     */
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

    /**
     * randomly select a new goal position for the maze.
     * this position will be different from the starting point.
     *
     * @throws ExceptionInInitializerError if all the boarders of the maze contains walls. //TODO: handle this issue
     */
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
            }
        }
        while (this.getStartPosition().equals(this.getGoalPosition()) || this.positionOfWall(this.getGoalPosition()));
    }

    /**
     * @param position a valid position in the maze
     * @return true if it contains WALL otherwise false. invalid position will return false too.
     */
    public boolean positionOfWall(Position3D position) {
        return this.validMazePosition(position) &&
                this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] == WALL;
    }

    /**
     * @param position a valid position in the maze
     * @return true if it contains TILE otherwise false. invalid position will return false too.
     */
    public boolean positionOfTile(Position3D position) {
        return this.validMazePosition(position) &&
                this.grid[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] == TILE;
    }

    /**
     * @param position position in the maze we wish to check
     * @return true for valid position in the maze, otherwise false
     */
    public boolean validMazePosition(Position3D position) {
        return (position != null &&
                0 <= position.getDepthIndex() && position.getDepthIndex() < this.getDepthSize() &&
                0 <= position.getRowIndex() && position.getRowIndex() < this.getRowsSize() &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.getColumnsSize());
    }
}
