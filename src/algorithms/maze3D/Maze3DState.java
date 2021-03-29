package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
    public Maze3DState(AState prevState, String stateName, int cost) {
        super(prevState, stateName, cost);
    }
}
