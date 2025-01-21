package ia.algo.recherche;

import java.util.Random;
import java.util.ArrayList;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.Misc;
import ia.framework.common.ArgParse;

import ia.framework.recherche.TreeSearch;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.SearchNode;

public class DFS extends TreeSearch{

    public DFS(SearchProblem prob, State intial_state){
        super(prob, intial_state);
    }

    public boolean solve() {
        // On commence à létat initial
        SearchNode node = SearchNode.makeRootSearchNode(initial_state);

        this.frontier.add(node);
        State state = node.getState();


        if (ArgParse.DEBUG)
            System.out.print("[\n"+state);

        while( !problem.isGoalState(state) ) {
            // Les actions possibles depuis cette état
            ArrayList<Action> actions = problem.getActions(state);
            
            if (ArgParse.DEBUG){
                System.out.print("Actions Possibles : {");
                System.out.println(Misc.collection2string(actions, ','));
            }

            this.frontier.remove(node);
            this.explored.add(state);
            for(Action a : actions){
                // Executer et passer a l'état suivant
                node = SearchNode.makeChildSearchNode(problem, node, a);
                state = node.getState();

                if(!this.explored.contains(state) && !this.frontier.contains(node)) {
                    this.frontier.addFirst(node);
                }
            }

            node=this.frontier.getFirst();
            state=node.getState();

            if (ArgParse.DEBUG)
                System.out.println("Action choisie: "+node.getAction());


            if (ArgParse.DEBUG)
                System.out.print(" + " +node.getAction()+ "] -> ["+state);
        } 

        // Enregistrer le noeud final
        end_node = node;
        
        if (ArgParse.DEBUG)
            System.out.println("]");

        return true;
    }
}
