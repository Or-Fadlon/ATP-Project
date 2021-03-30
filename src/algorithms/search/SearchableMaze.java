package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private final Maze maze;
    private final MazeState startState, goalState;

    /**
     * constructor
     *
     * @param maze 3D maze to solve
     * @throws IllegalArgumentException -> maze == null
     */
    public SearchableMaze(Maze maze) {
        if (maze == null)
            throw new IllegalArgumentException("cant handle null maze");
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

    /**
     * all optional 1 block moves in the maze from the given state
     *
     * @param state a valid state in the problem
     * @return all the possible next states of the given state
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) { //TODO: is it ok to move in slant when there is no ר??
        Position currentPosition = new Position(state.currentState);
        ArrayList<AState> possibleStates = new ArrayList<>();
        boolean upWall = maze.positionOfWall(currentPosition.getUpPosition()),
                downWall = maze.positionOfWall(currentPosition.getDownPosition()),
                rightWall = maze.positionOfWall(currentPosition.getRightPosition()),
                leftWall = maze.positionOfWall(currentPosition.getLeftPosition());
        if (maze.validMazePosition(currentPosition.getUpPosition()) && !upWall)
            possibleStates.add(new MazeState(state, currentPosition.getUpPosition().toString(), state.cost + 10));
        if (maze.validMazePosition(currentPosition.getUpRightPosition()) && !maze.positionOfWall(currentPosition.getUpRightPosition()) && (!upWall || !rightWall))
            possibleStates.add(new MazeState(state, currentPosition.getUpRightPosition().toString(), state.cost + 15));
        if (maze.validMazePosition(currentPosition.getRightPosition()) && !rightWall)
            possibleStates.add(new MazeState(state, currentPosition.getRightPosition().toString(), state.cost + 10));
        if (maze.validMazePosition(currentPosition.getDownRightPosition()) && !maze.positionOfWall(currentPosition.getDownRightPosition()) && (!downWall || !rightWall))
            possibleStates.add(new MazeState(state, currentPosition.getDownRightPosition().toString(), state.cost + 15));
        if (maze.validMazePosition(currentPosition.getDownPosition()) && !downWall)
            possibleStates.add(new MazeState(state, currentPosition.getDownPosition().toString(), state.cost + 10));
        if (maze.validMazePosition(currentPosition.getDownLeftPosition()) && !maze.positionOfWall(currentPosition.getDownLeftPosition()) && (!downWall || !leftWall))
            possibleStates.add(new MazeState(state, currentPosition.getDownLeftPosition().toString(), state.cost + 15));
        if (maze.validMazePosition(currentPosition.getLeftPosition()) && !leftWall)
            possibleStates.add(new MazeState(state, currentPosition.getLeftPosition().toString(), state.cost + 10));
        if (maze.validMazePosition(currentPosition.getUpLeftPosition()) && !maze.positionOfWall(currentPosition.getUpLeftPosition()) && (!upWall || !leftWall))
            possibleStates.add(new MazeState(state, currentPosition.getUpLeftPosition().toString(), state.cost + 15));
        return possibleStates;
    }
}
