package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState  extends AState{
    Position position;


    public MazeState(AState prevState, String stateName, int cost) {
        super(prevState, stateName, cost);
    }
}
