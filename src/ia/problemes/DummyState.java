package ia.problemes;

import java.lang.Integer;
import java.awt.geom.Point2D;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;

/**
 * Représente un état du problème générique
 *
 */

public class DummyState extends State implements HasHeuristic{

    // Un noeud est représenté par un entier 
    private int id;
 
    // Heuristique: distance à vol d'oiseau entre ici et le but
    // Une sous estimation du coût réel à venir.
    // calculée depuis la classe Dummy
    
    private double dist_to_goal=0;
    

    // la possiton du noeud (genérée aléatoirement)
    private Point2D pos;
    
    /**
     * Créer un état du problème générique
     * <p>A utiliser pour créer un état</p>
     * @param i l'identifiant de l'état (le noeud du graph)
     */
    
    public DummyState (int i){
        id = i;
    }

    /**
     * Constructeur par copie
     * Créer un état du problème générique depuis un autre
     * @param other l'état qu'on copie
     */
    
    public DummyState (DummyState other){
        id = other.id;
        dist_to_goal = other.dist_to_goal;
    }

    /* Setter pour la distance au but (l'heuristique) 
     * @param d distance au but
     */
    
    public void setDistToGoal(double d){
        dist_to_goal=d;
    }

    /* Setter pour la position 
     * @param p position 
     */
    
    public void setPosition(Point2D p){
        pos = p;
    }


    
    /* Getter pour la position 
     * @return position 
     */
    
    public Point2D getPosition(){
        return pos;
    }
    
    /* Getter pour l'id 
     * @return l'id 
     */
    
    public int getId(){
        return id;
    }
    
	public boolean equalsState(State o) {
        DummyState other = (DummyState) o;
        return (id == other.id) &&
            (dist_to_goal == other.dist_to_goal);
    }
    
    public DummyState cloneState() {
        return new DummyState(this);
	}
    
    public int hashState() {
        return 31 * Double.hashCode(dist_to_goal) + id;
    }
    
    @Override
	public String toString() { // +", "+pos.getX()+" "+pos.getY()
        return "{"+id+"}";
    }

    /**
     * {@inheritDoc}
     * <p>Pour ce problème l'heuristique est la distance
     * à vol d'oiseau au but.</p>
     */
    
    public double getHeuristic(){
        return dist_to_goal;
    }


    
}
