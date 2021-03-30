package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Best First Search algorithm
 */
public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        super("Best First Search", new PriorityQueue<>(new priceComparator()));
    }
}

class priceComparator implements Comparator<AState> {

    @Override
    public int compare(AState o1, AState o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}
