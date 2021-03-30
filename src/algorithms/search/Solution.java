package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Solution of a problem
 */
public class Solution {

    private final ArrayList<AState> path;
    private int cost;

    /**
     * constructor
     *
     * @param goal the goal state after running a search algorithm.
     */
    public Solution(AState goal) {
        AState temp = goal;
        this.path = new ArrayList<>();
        while (temp != null) {
            this.path.add(temp);
            temp = temp.prevState;
        }
        this.cost = path.get(0).cost;
        Collections.reverse(path);
    }

    /**
     * empty constructor
     */
    public Solution() {
        this.path = new ArrayList<>();
    }

    /**
     * @return ArrayList full of states of the problem solution
     */
    public ArrayList<AState> getSolutionPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }
}
