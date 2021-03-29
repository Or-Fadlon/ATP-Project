package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        super("Best First Search", new PriorityQueue<AState>(AState::compareTo));
    }
}
