package ia.framework.recherche;

import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.Misc;

/**
 * Une classe mère qui représente un algorithme de recherche
 * <p>Les algorithmes doivent concrétiser la méthode solve</p>
 *
 */
public abstract class TreeSearch {

    /**
     * Le problème à résoudre 
     */
    
    protected SearchProblem problem;

    /**
     * L'état initial
     */
    protected State initial_state;

    /** 
     * Le noeud trouvé par la recherche (la méthode solve)
     */
    protected SearchNode end_node=null;   

    /*
     * La liste des noeuds a étendre 
     */
    protected Queue<SearchNode> frontier = null;

    /*
     * La liste de noeuds déjà traités
     */
    protected HashSet<State> explored = new HashSet<State>();

    /*
     * La liste de noeuds déjà traités
     */
    protected HashMap<State, SearchNode> visited =
        new HashMap<State, SearchNode>();

    
    
    
    /**
     * Crée un algorithme de recherche
     * @param p Le problème à résoudre
     * @param s L'état initial 
     */
    public TreeSearch(SearchProblem p, State s){
        initial_state = s;
        problem = p;
    }

    /**
     * Retourne le noeud finale de la recherche
     *
     * @return le noeud final 
     */
       
    public SearchNode getEndNode(){
        return end_node;
    }

    
    /**
     * Lance la recherche pour résoudre le problème 
     * <p>A concrétiser pour chaque algorithme.</p>
     * <p>La solution devra être stockée dans end_node.</p>
     * @return Vrai si solution trouvé 
     */
    
    public abstract boolean solve();


    /**
     * Afficher la solution trouvée (la suite d'actions)
     * et quelques informations sur la recherche. 
     */
    
    public void printSuccess() {
        
        // Récupérer la suite d'actions depuis la racine 
        ArrayList<Action> solution = end_node.getPathFromRoot(); 
        
        // Afficher des trucs 
        System.out.print("Solution: "+ initial_state+ " > "); 
        System.out.print(Misc.collection2string(solution, '>'));
        System.out.println(" "+end_node.getState());
        System.out.println("Solved ! Explored "+
                           SearchNode.getTotalSearchNodes() +
                           " nodes. Max depth was "+
                           SearchNode.getMaxDepth() +
                           ". Solution cost is "+end_node.getCost());
        
        
    }


    /**
     * Retourne la solution ou null si pas de solution
     * @return la suite d'action depuis l'état initial
     */
    
    public ArrayList<Action> getSolution(){
        if(end_node==null)
            return null;
        return end_node.getPathFromRoot(); 
    }
    
    
    /**
     * Afficher la solution trouvée (la suite d'actions)
     *   
     * Utiliser en cas d'échec 
     */
 
    public void printFailure() {
        
        // Afficher des trucs 
        System.out.println("Pas de solution depuis l'état : "+ initial_state);
    }

    /**
     * Retourne la Frontière
     *
     * @return une collection de SearchNode dans la frontière
     */ 
    public ArrayList<SearchNode> getFrontier(){
        ArrayList<SearchNode> list = new ArrayList<SearchNode>( this.frontier );
        return list;
    }
    
    /**
     * Retourne l'ensemble des états visités 
     *
     * @return un ensemble de State
     */ 
    public Set<State> getVisited(){
        return this.visited.keySet();
    }

    
}
