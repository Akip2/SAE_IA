package ia.framework.recherche;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.StateActionPair;
import ia.framework.common.Misc;


/**
* Un Probleme où les états et les transitions sont spécifiées. 
*
*
* <p>Cette classe ajoute un ensemble détat et de transition à {@link SearchProblem}. Pour un exemple, voir la classe {@link ia.problemes.Vacuum})</p>
*
*/

public abstract class Problem extends SearchProblem {

    /**
     * La liste des états a remplir (voir {@link ia.problemes.Vacuum})
     */
    
    protected static State[] STATES = null ;

    /**
     * La liste des transition a remplir (voir {@link ia.problemes.Vacuum})
     */
    protected static Transitions TRANSITIONS = new Transitions();
    
    
    public ArrayList<Action> getActions(State s){
        // rechercher dans toutes les transitions celle qui partent de s
        Set<StateActionPair> sa = TRANSITIONS.getKeys();
        List<StateActionPair> state = sa.stream()
            .filter(k -> s.equals(k.getState()))
            .collect(Collectors.toList());

        // récupérer les action depuis s
        List<Action> actions = state
            .stream()
            .map(k -> k.getAction())
            .collect(Collectors.toList());

        // On trie pour les avoir dans le même ordre a chaque fois 
        ArrayList<Action> result = new ArrayList<Action>(actions);
        Collections.sort(result) ;
        return result;
    }
    
    // exécute l'action a dans l'état s, retourne le nouvel état
    public State doAction(State s, Action a){
        return TRANSITIONS.getTransition(s,a);
    }
    
    // retourne le coût de faire l'action a dans l'état s
    public double getActionCost(State s, Action a){
        return TRANSITIONS.getCost(s,a);
    }

    /**
     * Affiche le graphe des états du problème.
     *
     * @return represenation du graphe
     */ 
    public String printStateTransitions(){
        String res ="";
        for(int i=0; i< STATES.length; i++){
            res = res + STATES[i] + "{"
                + Misc.collection2string(getActions(STATES[i]), ',')
                + "}\n" ;
        }
        
        return res;
    }
    
    /**
     * {@inheritDoc}
     * Affiche description, les états, action et transitions 
     */
     public String getDescription(){
        String res = desc+"\n";
        return res+this;
        
    } 
    

    /**
     * Représentation String d'un problème 
     * @return la representation 
     */
    public String toString(){
        String res = "Nombre d'etats |S| = "+STATES.length;
        
        res += "\nS = {";

        int lb = STATES.length;
        if(STATES.length > 15)
            lb=5;
        for(int i=0; i<lb; i++)
            res += STATES[i] + ", ";
        if(STATES.length > 15){
            res+= "...";
            for(int i=STATES.length-lb; i<STATES.length; i++)
                res += STATES[i] + ", ";
        }
        res += "}\nA = { " ;
        for(int i=0; i< ACTIONS.length; i++)
           res += ACTIONS[i] + ", ";

        res += "}\nT ={ ";
        res += printStateTransitions();
        return res+"}";
    }

    /**
     * {@inheritDoc}
     * 
     */
    public String toDot(State s0, ArrayList<Action> sol,
                        Set<State> vis){
        String res ="digraph Dummy{\n";

        // états
        res += "\t\"" + s0 +"\" [fillcolor=red style=filled];\n";
        for(int i=0; i< STATES.length; i++){
            State s = STATES[i];

            if(isGoalState(s))
                res += "\t\"" + s +"\" [fillcolor=green style=filled];\n";
            else 
                res += "\t\"" + s +"\";\n";
            
        }

        // transitions 
        for (StateActionPair sa: TRANSITIONS.getKeys()){
            State s1 = sa.getState();
            Action a = sa.getAction();
            State s2 = TRANSITIONS.getTransition(s1,a);
            
            double c = TRANSITIONS.getCost(s1,a);
            res += "\t\""
                + s1 + "\"->\""
                + s2 +"\" [label=\""+ a+"\" weight="+c+"];\n";
        }

        // solution 
        
        if(sol!=null){
            State s1 = s0 ; 
            for(Action a: sol){
                State s2 = TRANSITIONS.getTransition(s1,a);
                res += "\t\""
                    + s1 + "\"->\""
                    + s2 + "\" [color=red, penwidth=2];\n";
                s1=s2;
            }

        }
        
        // visité 
        if(vis!=null)
            for(State s: vis)
                if(! s.equals(s0) && !isGoalState(s))
                    res += "\t\"" + s +"\" [fillcolor=yellow style=filled];\n";
        
        
        res += "}\n" ;
        return res;
    }


    
}
