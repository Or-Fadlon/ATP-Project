package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private final Maze3D maze;
    private final Maze3DState startState, goalState;

    /**
     * constructor
     *
     * @param maze 3D maze to solve
     * @throws IllegalArgumentException -> maze == null
     */
    public SearchableMaze3D(Maze3D maze) {
        if (maze == null)
            throw new IllegalArgumentException("cant handle null maze");
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

    /**
     * all optional 1 block moves in the maze from the given state
     *
     * @param state a valid state in the problem
     * @return all the possible next states of the given state
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        Position3D currentPosition = new Position3D(state.toString());
        ArrayList<AState> possibleStates = new ArrayList<>();
        if (maze.validMazePosition(currentPosition.getUpPosition()) && !maze.positionOfWall(currentPosition.getUpPosition())) // UP
            possibleStates.add(new Maze3DState(state, currentPosition.getUpPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getRightPosition()) && !maze.positionOfWall(currentPosition.getRightPosition())) // RIGHT
            possibleStates.add(new Maze3DState(state, currentPosition.getRightPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getDownPosition()) && !maze.positionOfWall(currentPosition.getDownPosition())) // DOWN
            possibleStates.add(new Maze3DState(state, currentPosition.getDownPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getLeftPosition()) && !maze.positionOfWall(currentPosition.getLeftPosition())) // LEFT
            possibleStates.add(new Maze3DState(state, currentPosition.getLeftPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getHigherPosition()) && !maze.positionOfWall(currentPosition.getHigherPosition())) // HIGH
            possibleStates.add(new Maze3DState(state, currentPosition.getHigherPosition().toString(), 1));
        if (maze.validMazePosition(currentPosition.getLowerPosition()) && !maze.positionOfWall(currentPosition.getLowerPosition())) //LOW
            possibleStates.add(new Maze3DState(state, currentPosition.getLowerPosition().toString(), 1));

        return possibleStates;
    }
}
