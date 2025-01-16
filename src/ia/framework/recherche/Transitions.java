package ia.framework.recherche;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.StateActionPair;
import ia.framework.common.Misc;

/**
 * Représentes la liste des transisions entre états
 *  
 *
 */

public class Transitions {

    private Map<StateActionPair, State> transition =
        new HashMap<StateActionPair, State>();

    private Map<StateActionPair, Double> costs =
        new HashMap<StateActionPair, Double>();

    private Set<StateActionPair> keys = null;

    // Ajoute un transition a la liste de transitions
    
    public void addTransition(State s1, Action a, State s2, double c){
        StateActionPair sa = new StateActionPair(s1, a);
        this.transition.put(sa, s2);
        this.costs.put(sa, c);
    }

    public void delTransition(State s1, Action a){
        StateActionPair sa = new StateActionPair(s1, a);
        this.transition.remove(sa);
        this.costs.remove(sa);
    }
    
    public State getTransition(State s, Action a){
        return this.transition.get(new StateActionPair(s, a));
    }
    
    public double getCost(State s, Action a){
        //System.out.println(s+" "+a);
        StateActionPair sa = new StateActionPair(s, a);
        //        System.out.println(sa);

        double cost = this.costs.get(sa);
        //System.out.println(cost);
        return cost;
    }

    // retourn les couples (état, action) de la liste 
    public Set<StateActionPair> getKeys(){
        if(this.keys == null)
            this.keys =  this.transition.keySet();
        return this.keys;
    }

    public String toString(){
        String res ="";
        Set<StateActionPair> keys_list = getKeys();

        res += "\nKeys : Transitions & costs\n";
        for (StateActionPair k: keys_list)
            res += k+": " + transition.get(k)+" "+costs.get(k)+"\n";
        return res;
    }
}
