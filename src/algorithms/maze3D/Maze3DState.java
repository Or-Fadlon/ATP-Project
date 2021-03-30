package algorithms.maze3D;

import algorithms.search.AState;

/**
 * 2D Maze problem state
 */
public class Maze3DState extends AState {
    public Maze3DState(AState prevState, String currentState, int cost) {
        super(prevState, currentState, cost);
    }
}
