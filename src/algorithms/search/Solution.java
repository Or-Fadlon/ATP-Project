package algorithms.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution {

    public int cost;
    private ArrayList<AState> path;

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

    public Solution() {
    }

    public ArrayList<AState> getSolutionPath() {
        return path;
    }
}
