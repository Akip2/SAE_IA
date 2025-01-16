package ia.problemes;

import java.util.Arrays;

import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;

/**
 * Représente une état du problème du voyage en Roumanie
 *
 */

public class RomaniaMapState extends State implements HasHeuristic{

    
    // Le nom de la ville

    private String name=null;

    // Heuristique: distance à vol d'oiseau entre ici et Bucharest
    // Une sous estimation du coût réel à venir
    
    private double dist_to_goal=0;
    
    /**
     * Crée une ville (état du problème)
     * @param n le nom e de la ville
     * @param d la distance a vole d'oiseau de Bucharest
     * (la valeur de heuristique)
     */
        
    public RomaniaMapState(String n, double d){
        name = n;
        dist_to_goal = d; // l'heuristique
    }

    public State cloneState(){
        return new RomaniaMapState(name, dist_to_goal);
    }

    public boolean equalsState(State o){
        RomaniaMapState other = (RomaniaMapState) o;
        return (other.dist_to_goal == dist_to_goal) &&
            (name.equals(other.name));
    }

    public int hashState(){
        return 31 * Double.hashCode(dist_to_goal) + name.hashCode();
    }

    @Override
	public String toString() {
        return "{"+name+","+dist_to_goal+"}";
    }

    /**
     * {@inheritDoc}
     * <p>Pour ce problème l'heuristique est la distance
     * à vol d'oiseau.</p>
     */
    
    public double getHeuristic(){
        return dist_to_goal;
    }

}
