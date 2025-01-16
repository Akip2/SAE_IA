package ia.algo.recherche;

import java.lang.Math;
import java.util.ArrayList;
import java.util.LinkedList;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.Misc;
import ia.framework.common.ArgParse;

import ia.framework.recherche.TreeSearch;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.SearchNode;

public class RandomTreeSearch extends TreeSearch{
     
    public RandomTreeSearch(SearchProblem prob, State initial_state){
        super(prob, initial_state);
        this.frontier = new LinkedList<SearchNode>();
    }

    public boolean solve() {

        // 1 Créer un noeud correspondant à l'état initial
        SearchNode root_node =
            SearchNode.makeRootSearchNode(initial_state);

        // 2 Initialiser la frontière avec ce noeud
        this.frontier.clear();
        this.frontier.add(root_node);

        // 3 Initialiser l'ensemble des états visités à vide 
        this.explored.clear();
        
        // 4 Tant que la frontière n'est pas vide
        while (! this.frontier.isEmpty()) {
       
            // 5 Retirer un noeud de la frontière selon une stratégie  
            //   Note : ici un noeud aléatoire
            
            int rnd = (int) (Math.random() * this.frontier.size());
            SearchNode cur_node = ((LinkedList<SearchNode>) this.frontier).remove(rnd);
            
            // 6 Si le noeud contient un état but
            // 7 retourner vrai // le noeud est stocker dans end_node
            State cur_state = cur_node.getState();
            if (problem.isGoalState(cur_state)){ // si but 
                this.end_node = cur_node;
                return true;
            }

            // 8 Sinon
            // 9 Ajouter son état à l'ensemble des états visités
            this.explored.add(cur_state);

            // 10 Étendre les enfants du noeud
            // les actions possible depuis ce noeud
            ArrayList<Action> actions = problem.getActions(cur_state);
            if (ArgParse.DEBUG)
                System.out.print(cur_state+" ("+actions.size()+" actions) -> {");

            // 11 Pour chaque noeud enfant
            // générer tous les noeuds enfants du noeud courant
            for (Action a : actions){
                
                // on en fait un Noeud
                SearchNode child_node =
                    SearchNode.makeChildSearchNode(problem, cur_node, a);
               
                State child_state = child_node.getState();
                if (ArgParse.DEBUG)
                    System.out.print("("+a+", "+child_state+")");
                
                //12 S'il n'est pas dans la frontière et
                //   si son état n'a pas été visité  
                if (!frontier.contains(child_node) &&
                    !explored.contains(child_state)){
                    
                    // 13 L'insérer dans la frontière
                    frontier.add(child_node);
                    
                    if (ArgParse.DEBUG)
                        System.out.print("[A] "); // ajouter
                    
                } else {
                    if (ArgParse.DEBUG)
                        System.out.print("[I] "); // ignorer
                }
            }
            if(actions.size()>0)
                if (ArgParse.DEBUG)
                    System.out.println("}");
        }
        if (ArgParse.DEBUG)
            System.out.println();
        return false;
    }
}
