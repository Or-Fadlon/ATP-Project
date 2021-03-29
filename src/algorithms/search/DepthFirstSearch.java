package algorithms.search;

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch() {
        super("Depth First Search");
    }

    /***
     *procedure DFS_iterative(G, v) is
     *     let S be a stack
     *     S.push(v)
     *     while S is not empty do
     *         v = S.pop()
     *         if v is not labeled as discovered then
     *             label v as discovered
     *             for all edges from v to w in G.adjacentEdges(v) do
     *                 S.push(w)
     * @param domain
     * @return
     */
    @Override
    public Solution solve(ISearchable domain) {
        Stack<AState> stack = new Stack<>();
        HashSet<String> visited = new HashSet<>();
        AState currentState;
        stack.push(domain.getStartState());

        while (!stack.isEmpty()) {
            currentState = stack.pop();
            if (currentState.equals(domain.getGoalState())) {
                this.NumberOfNodesEvaluated = visited.size();
                return new Solution(currentState);
            }
            if (!visited.contains(currentState.toString())) {
                visited.add(currentState.toString());
                stack.addAll(domain.getAllPossibleStates(currentState));
            }
        }
        this.NumberOfNodesEvaluated = visited.size();
        return new Solution();
    }
}
