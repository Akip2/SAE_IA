package ia.framework.recherche;

import java.util.Collections;
import java.util.ArrayList;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.BaseProblem;

/**
 * Représente un noeud de l'arbre de recherche
 *
 */

public class SearchNode {
    /**
     * Un compteur de noeud créés
     */
    protected static int COUNT = 0;
    
    /**
     * La profondeur max atteinte
     */
    protected static int DEPTH = 0; 

    /**
     * Un id pour les différencier à l'affichage 
     */
    protected int id;
    
    /**
     * Le noeud parent de ce noeud
     */
    protected SearchNode parent; 

    /**
     * L'état stocké dans le noeud
     */
    protected State state; 

    /**
     * L'action depuis le parent a ici 
     */
    protected Action action;
    
    /**
     * La profondeur du noeud 
     */
    protected int depth; 

    /**
     * Le coût du chemin depuis la racine a moi
     */
    private double path_cost;

    /** 
     * @return Le nombre total de noeud créés (visités) 
     */
    public static int getTotalSearchNodes() {
        return COUNT;
    }

    /** 
     * @return La profondeur maximum atteinte 
     */
    public static int getMaxDepth() {
        return DEPTH;
    }

    /**
     * Factory pour créer un noeud racine correspondant à l'état
     * initial 
     * <p>Le parent est null le coût=0, et pas d'action arrivant
     * ici </p>
     * @param s L'état initial  
     * @return Le noeud correspondant à l'état initial  
     */
    
    public static SearchNode makeRootSearchNode(State s){
        return new SearchNode(null, s, null, 0);
    }

    /** 
     * Factory pour créer un noeud de l'arbre de recherche 
     * @param pb Le problème
     * @param par Le parent de ce noeud
     * @param a L'action exécuté sur le parent qui arrive ici 
     */
    public static SearchNode makeChildSearchNode(SearchProblem pb,
                                                 SearchNode par,
                                                 Action a) {
        // on exécute l'action et calcul le coût
        
        return new SearchNode(par,
                              pb.doAction(par.getState(), a), a,
                              pb.getActionCost(par.getState(), a) +
                              par.getCost());
    }

    /**
     * Crée un noeud 
     * @param p Le noeud parent
     * @param s L'état du ce noeud
     * @param a L'action qui mène du parent a ce neoud
     * @param c Le coût de faire l'action 
     */
    public SearchNode(SearchNode p, State s, Action a, double c){
        id = COUNT++;
        state = s;
        action = a;
        parent = p;
        depth = 0;
        if (p != null) // on est pas une racine
            depth = p.getDepth() + 1; 
        if (depth > DEPTH)
            DEPTH=depth;
        
        path_cost = c;
    }
    
    /**
     * @return L'id de ce noeud 
     */
    
    public int getId(){
        return id;
    }
    
    /**
     * @return La profondeur de ce noeud 
     */
    
    public int getDepth(){
        return depth;
    }
    
    /**
     * @return Le parent de ce noeud 
     */

    public SearchNode getParent(){
        return parent;
    }

    /**
     * @return L'action qui mène à ce noeud 
     */
    
    public Action getAction(){
        return action;
    }

    /** 
     * @return L'état du problème de ce noeud 
     */
    
    public State getState(){
        return state;
    }

    /**
     * @return Le coût du chemin de la racine à ce noeud
     */
    
    public double getCost(){
        return path_cost;
    }

    /**
     * Calcule l'heuristique (sous-estimation du coût au but)
     * <p>Valable uniquement pour certain type de problèmes
     * (implantant {@link HasHeuristic}),
     * pour les autres on retourne zéro.</p>
     * @return La valeur le heuristique
     */
    
    public double getHeuristic(){
        if(!(state instanceof HasHeuristic))
            return 0;
        return ((HasHeuristic) state).getHeuristic(); 
    }

    /**
     * @return Une représentation du noeud 
     */
    public String toString() {
        if(parent != null)
            return "("+id+", " + parent.getId() + ", " +path_cost+")";
        return "("+id+", root, " +path_cost+")";
    }

    /**
     * Test l'égalité avec un autre Noeud
     * <p>On test l'égalité sur les états</p>
     * @param o L'autre noeud
     * @return Vrai si les deux noeuds contient des états égaux 
     */
    
    @Override
	public boolean equals(Object o) {
		if (o != null && getClass() == o.getClass()) {
			SearchNode other = (SearchNode) o;
			return state.equals(other.getState());  
		}
		return false;
	}       
   
    /**
     * Calcule le chemin (la liste d'actions) depuis la racine à
     * ce noeud.
     * <p>Le chemin est construit en suivant les parents, jusqu'à la
     * racine. </p>
     *  
     * @return la liste d'action deoui l
     */
    
    public ArrayList<Action> getPathFromRoot(){
        ArrayList<SearchNode> path = new ArrayList<SearchNode>();
        SearchNode curent = this;

        // Aller à la racine
        do{
            path.add(curent);
            curent = (SearchNode)curent.getParent();
        } while (curent != null);

        // Inverser pour commencer par la racine
        Collections.reverse(path);

        // Extraire les actions du chemin
        ArrayList<Action> solution = new ArrayList<Action>(); 
        for(SearchNode n: path)
            if (n.getParent() != null) // ignorer la racine 
                solution.add(n.getAction());

        return solution;
    }
}
    
    
        
        


