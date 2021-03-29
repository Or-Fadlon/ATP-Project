package algorithms.maze3D;

public class Maze3D {
    int[][][] grid;
    Position3D startPosition, goalPosition;

    public int[][][] getMap() {
        return grid;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }
}
