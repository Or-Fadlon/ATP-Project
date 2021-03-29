package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    private Queue<AState> queue = new LinkedList<>();

    public BreadthFirstSearch() {
        super("Breadth First Search");
    }

    protected BreadthFirstSearch(String name, Queue<AState> queue) {
        super(name);
        this.queue = queue;
    }

    /***
     *  1  procedure BFS(G, root) is
     *  2      let Q be a queue
     *  3      label root as discovered
     *  4      Q.enqueue(root)
     *  5      while Q is not empty do
     *  6          v := Q.dequeue()
     *  7          if v is the goal then
     *  8              return v
     *  9          for all edges from v to w in G.adjacentEdges(v) do
     * 10              if w is not labeled as discovered then
     * 11                  label w as discovered
     * 12                  Q.enqueue(w)
     * @param domain
     * @return
     */
    @Override
    public Solution solve(ISearchable domain) {
        HashSet<String> visited = new HashSet<>();
        AState currentState;
        this.queue.clear();

        visited.add(domain.getStartState().toString());
        queue.add(domain.getStartState());

        while (!queue.isEmpty()) {
            currentState = queue.remove();
            if (currentState.equals(domain.getGoalState())) {
                this.NumberOfNodesEvaluated = visited.size();
                return new Solution(currentState);
            }
            for (AState a : domain.getAllPossibleStates(currentState)) {
                if (!visited.contains(a.toString())) {
                    visited.add(a.toString());
                    queue.add(a);
                }
            }
        }
        this.NumberOfNodesEvaluated = visited.size();
        return new Solution();
    }
}
