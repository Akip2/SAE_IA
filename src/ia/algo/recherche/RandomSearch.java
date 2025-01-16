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

public class RandomSearch extends TreeSearch{
       
    public RandomSearch(SearchProblem prob, State intial_state){
        super(prob, intial_state);
    }

    public boolean solve() {
         Random rng = new Random();
         
        // On commence à létat initial
        SearchNode node = SearchNode.makeRootSearchNode(initial_state);
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

            // En chosir une au hasard
            Action a = actions.get(rng.nextInt(actions.size()));
            if (ArgParse.DEBUG)
                System.out.println("Action choisie: "+a);

                
            // Executer et passer a l'état suivant
            node = SearchNode.makeChildSearchNode(problem, node, a);
            state = node.getState();

            if (ArgParse.DEBUG)
                System.out.print(" + " +a+ "] -> ["+state);
        } 

        // Enregistrer le noeud final
        end_node = node;
        
        if (ArgParse.DEBUG)
            System.out.println("]");

        return true;
    }
}
