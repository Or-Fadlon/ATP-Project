package algorithms.search;

/**
 * abstract State in searching problem
 */
public abstract class AState {

    private final AState prevState;
    private final Object currentState; //TODO: change to Object?? POSITION?? is it ok to assume that every node have unique
    private final int cost;

    public AState(AState prevState, Object currentState, int cost) {
        this.currentState = currentState;
        this.prevState = prevState;
        this.cost = cost;
    }

    public AState getPrevState() {
        return prevState;
    }

    public Object getCurrentState() {
        return currentState;
    }

    public int getCost() {
        return cost;
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
        return this.currentState.toString();
    }
}
