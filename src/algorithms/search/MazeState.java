package algorithms.search;

/**
 * 2D Maze problem state
 */
public class MazeState extends AState {
    public MazeState(AState prevState, String stateName, int cost) {
        super(prevState, stateName, cost);
    }
}
