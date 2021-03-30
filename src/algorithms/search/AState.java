package algorithms.search;

/**
 * abstract State in searching problem
 */
public abstract class AState implements Comparable<AState> {

    protected String currentState; //TODO: change to ASTATE?? POSITION?? is it ok to assume that every node have unique
    protected AState prevState;
    protected int cost;

    public AState(AState prevState, String currentState, int cost) {
        this.currentState = currentState;
        this.prevState = prevState;
        this.cost = cost;
    }

    /**
     * @param other AState to compare
     * @return the value 0 if this == other;
     * a value less than 0 if this < other; and a value greater than 0 if this > other
     */
    @Override
    public int compareTo(AState other) {
        return Integer.compare(this.cost, other.cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return currentState != null ? currentState.equals(aState.currentState) : aState.currentState != null;
    }

    @Override
    public String toString() {
        return this.currentState;
    }
}
