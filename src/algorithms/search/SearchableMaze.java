package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    public Maze maze;
    private MazeState startState, goalState;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.startState = new MazeState(null, maze.getStartPosition().toString(), 0);
        this.goalState = new MazeState(null, maze.getGoalPosition().toString(), 0);
    }

    @Override
    public AState getStartState() {
        return this.startState;
    }

    @Override
    public AState getGoalState() {
        return this.goalState;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        Position currentPosition = new Position(state.stateName);
        ArrayList<AState> possibleStates = new ArrayList<>();
        if (maze.validMazePosition(currentPosition.getUpPosition()) && !maze.positionOfWall(currentPosition.getUpPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getUpPosition().toString(), 10));
        if (maze.validMazePosition(currentPosition.getUpRightPosition()) && !maze.positionOfWall(currentPosition.getUpRightPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getUpRightPosition().toString(), 15));
        if (maze.validMazePosition(currentPosition.getRightPosition()) && !maze.positionOfWall(currentPosition.getRightPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getRightPosition().toString(), 10));
        if (maze.validMazePosition(currentPosition.getDownRightPosition()) && !maze.positionOfWall(currentPosition.getDownRightPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getDownRightPosition().toString(), 15));
        if (maze.validMazePosition(currentPosition.getDownPosition()) && !maze.positionOfWall(currentPosition.getDownPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getDownPosition().toString(), 10));
        if (maze.validMazePosition(currentPosition.getDownLeftPosition()) && !maze.positionOfWall(currentPosition.getDownLeftPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getDownLeftPosition().toString(), 15));
        if (maze.validMazePosition(currentPosition.getLeftPosition()) && !maze.positionOfWall(currentPosition.getLeftPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getLeftPosition().toString(), 10));
        if (maze.validMazePosition(currentPosition.getUpLeftPosition()) && !maze.positionOfWall(currentPosition.getUpLeftPosition()))
            possibleStates.add(new MazeState(state, currentPosition.getUpLeftPosition().toString(), 15));
        return possibleStates;
    }
}
