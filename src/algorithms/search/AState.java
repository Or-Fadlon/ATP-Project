package algorithms.search;

public class AState implements Comparable<AState> {

    protected String stateName;
    protected AState prevState;
    protected int cost;

    public AState(AState prevState, String stateName, int cost) {
        this.stateName = stateName;
        this.prevState = prevState;
        this.cost = cost;
    }

    @Override
    public int compareTo(AState other) {
        return Integer.compare(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return this.stateName;
    }
}
