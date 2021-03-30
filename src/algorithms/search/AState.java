package algorithms.search;

/**
 * abstract State in searching problem
 */
public abstract class AState implements Comparable<AState> {

    protected String stateName;
    protected AState prevState;
    protected int cost;

    public AState(AState prevState, String stateName, int cost) {
        this.stateName = stateName;
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
        return stateName != null ? stateName.equals(aState.stateName) : aState.stateName != null;
    }

    @Override
    public String toString() {
        return this.stateName;
    }
}
