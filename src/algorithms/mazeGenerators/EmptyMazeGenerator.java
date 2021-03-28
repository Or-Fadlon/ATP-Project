package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        maze.cleanAllWalls();
        maze.generateStartPosition();
        maze.generateGoalPosition();
        return maze;
    }
}
