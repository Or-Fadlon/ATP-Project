package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private final String name;
    protected int NumberOfNodesEvaluated;

    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }
}
