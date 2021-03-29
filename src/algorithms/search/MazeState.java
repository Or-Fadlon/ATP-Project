package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState  extends AState{
    private Position position;


    public MazeState(AState prevState, String stateName, int cost) {
        super(prevState, stateName, cost);
        this.position = new Position(stateName);
    }

    public Position getPosition(){
        return this.position;
    }
}
