package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Solution of a problem
 */
public class Solution {

    public int cost;
    private ArrayList<AState> path;

    /**
     * constructor
     *
     * @param goal the goal state after running a search algorithm.
     */
    public Solution(AState goal) {
        AState temp = goal;
        this.path = new ArrayList<>();
        this.cost = 0;
        while (temp != null) {
            this.path.add(temp);
            cost += temp.cost;
            temp = temp.prevState;
        }
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
}
