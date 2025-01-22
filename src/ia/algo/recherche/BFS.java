package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class BFS extends TreeSearch {

    public BFS(SearchProblem prob, State initial_state){
        super(prob, initial_state);
    }

    public boolean solve() {
        this.frontier = new LinkedList<SearchNode>();
        this.frontier.add(SearchNode.makeRootSearchNode(this.initial_state));

        while (!frontier.isEmpty()) {
            SearchNode node = frontier.removeFirst();
            State state = node.getState();

            this.explored.add(state);

            if(problem.isGoalState(state)) {
                end_node = node;
                return true;
            }

            ArrayList<Action> actions = problem.getActions(state);
            for (Action action : actions) {
                SearchNode newNode = SearchNode.makeChildSearchNode(problem, node, action);
                State newState = newNode.getState();

                if(!this.explored.contains(newState) && !frontier.contains(newNode)){
                    this.frontier.add(newNode);
                }
            }
        }

        end_node = SearchNode.makeRootSearchNode(initial_state);
        return false;
    }
}