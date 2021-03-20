package algorithms.mazeGenerators;

import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    static Position getUnvisitedNeighbour(int[][] visited, Position position) {
        Random rand = new Random();
        int x = position.getColumnIndex(), y = position.getRowIndex();
        Position[] unvisited = new Position[4];
        int unvisitedCounter = 0;
        if (x + 1 < visited.length && visited[x + 2][y] == 0)
            unvisited[unvisitedCounter++] = new Position(x + 1, y);
        if (x - 1 >= 0 && visited[x - 1][y] == 0)
            unvisited[unvisitedCounter++] = new Position(x - 1, y);
        if (y + 1 < visited[0].length && visited[x][y + 2] == 0)
            unvisited[unvisitedCounter++] = new Position(x, y + 1);
        if (y - 1 >= 0 && visited[x][y - 1] == 0)
            unvisited[unvisitedCounter++] = new Position(x, y - 1);

        if (unvisitedCounter == 0)
            return null;
        else
            return unvisited[rand.nextInt(unvisitedCounter)];
    }

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        Stack<Position> stack = new Stack<>();
        int[][] visited = new int[rows][columns];
        Position position = maze.getStartPosition(), unvisitedNeighbour;

        maze.makeAllWalls();
        visited[position.getRowIndex()][position.getColumnIndex()] = 1;
        stack.push(position);
        while (!stack.isEmpty()) {
            position = stack.pop();
            unvisitedNeighbour = getUnvisitedNeighbour(visited, position);
            if (unvisitedNeighbour != null) {
                visited[unvisitedNeighbour.getRowIndex()][unvisitedNeighbour.getColumnIndex()] = 1;
                maze.removeWall(unvisitedNeighbour.getRowIndex(), unvisitedNeighbour.getColumnIndex());
                stack.push(unvisitedNeighbour);
                stack.push(position);
            }
        }
        return maze;
    }
}
