package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends TreeSearch {

    public AStar(SearchProblem prob, State initial_state){
        super(prob, initial_state);
    }

    public boolean solve() {
        PriorityQueue<SearchNode> frontier = new PriorityQueue<>(
                new Comparator<SearchNode>() {
                    @Override
                    public int compare(SearchNode o1, SearchNode o2) {
                        return (int) ( (o1.getCost()+o1.getHeuristic()) - (o2.getCost() + o2.getHeuristic()) );
                    }
                }
        );

        frontier.add(SearchNode.makeRootSearchNode(this.initial_state));

        while (!frontier.isEmpty()) {
            SearchNode node = frontier.poll();
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
                    frontier.add(newNode);
                }
            }
        }

        end_node = SearchNode.makeRootSearchNode(initial_state);
        return false;
    }
}