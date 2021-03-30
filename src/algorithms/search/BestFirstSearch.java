package algorithms.search;

import java.util.PriorityQueue;

/**
 * Best First Search algorithm
 */
public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        super("Best First Search", new PriorityQueue<AState>());
    }
}
