package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        Random random = new Random();
        Position currentPosition;
        maze.generateStartPosition();
        maze.generateGoalPosition();
        maze.makeAllWalls();
        currentPosition = maze.getStartPosition();
        maze.removeWall(currentPosition);
        maze.removeWall(maze.getGoalPosition());
        while (!currentPosition.equals(maze.getGoalPosition())) {
            if (random.nextInt(2) == 0) {
                if (currentPosition.getRowIndex() > maze.getGoalPosition().getRowIndex())
                    currentPosition = currentPosition.getUpPosition();
                if (currentPosition.getRowIndex() < maze.getGoalPosition().getRowIndex())
                    currentPosition = currentPosition.getDownPosition();
                maze.removeWall(currentPosition);
            }
            else {
                if (currentPosition.getColumnIndex() > maze.getGoalPosition().getColumnIndex())
                    currentPosition = currentPosition.getLeftPosition();
                if (currentPosition.getColumnIndex() < maze.getGoalPosition().getColumnIndex())
                    currentPosition = currentPosition.getRightPosition();
                maze.removeWall(currentPosition);
            }
        }
        return maze;
    }
}

