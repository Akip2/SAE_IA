package ia.problemes;

import java.util.Arrays;

import ia.framework.common.State;


/**
 * Représente un état du problème de notre robot aspirateur
 */
public class VacuumState extends State {

    /**
     * Les 2 pièce avec vrai si sale 
     */
    
    private boolean[] rooms = null;

    /**
     * La position du robot 0="a gauche" 
     */
    private int position = 0;

    /**
     * Crée un état les deux pièces sont sales
     * et le robot est a gauche
     */
    public VacuumState(){
        rooms = new boolean[] {true, true};
        position = 0;
    }

    /**
     * Crée un état avec les information données
     * @param p La position du robot
     * @param r L'état des deux pièces
     *
     */
    public VacuumState(int p, boolean[] r){
        rooms = r.clone();
        position = p;
    }

  
    public State cloneState(){
        return new VacuumState(position, rooms);
    }

    public boolean equalsState(State o){
        VacuumState other = (VacuumState) o;
        return (other.position == position) &&
            (Arrays.equals(rooms, other.rooms));
    }

    public int hashState(){
        return 31 * position + Arrays.hashCode(rooms);
    }

    @Override
	public String toString() {
        return "["+((position==0)?"R,":"_,")+((rooms[0])?"D|":"_|")+
            ((position==1)?"R,":"_,")+((rooms[1])?"D]":"_]");
    }
}

