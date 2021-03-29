package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;
    private Maze3DState startState, goalState;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
        this.startState = new Maze3DState(null, maze.getStartPosition().toString(), 0);
        this.goalState = new Maze3DState(null, maze.getGoalPosition().toString(), 0);
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
        Position3D currentPosition = new Position3D(state.toString());
        ArrayList<AState> possibleStates = new ArrayList<>();
        if (maze.validMazePosition(currentPosition.getUpPosition()) && !maze.positionOfWall(currentPosition.getUpPosition()))
            possibleStates.add(new Maze3DState(state, currentPosition.getUpPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getRightPosition()) && !maze.positionOfWall(currentPosition.getRightPosition()))
            possibleStates.add(new Maze3DState(state, currentPosition.getRightPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getDownPosition()) && !maze.positionOfWall(currentPosition.getDownPosition()))
            possibleStates.add(new Maze3DState(state, currentPosition.getDownPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getLeftPosition()) && !maze.positionOfWall(currentPosition.getLeftPosition()))
            possibleStates.add(new Maze3DState(state, currentPosition.getLeftPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getHigherPosition()) && !maze.positionOfWall(currentPosition.getHigherPosition()))
            possibleStates.add(new Maze3DState(state, currentPosition.getHigherPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getLowerPosition()) && !maze.positionOfWall(currentPosition.getLowerPosition()))
            possibleStates.add(new Maze3DState(state, currentPosition.getLowerPosition().toString(), 1));

        return possibleStates;
    }
}
