package ia.framework.common;

/**
 * Représente une action 
 * 
 */

public class Action implements Comparable<Action> {

    /**
     * Une action est juste une instance qui a un nom  
     * pour mieux les lire :) 
     */
    
    private final String name; 

    /**
     * @param n le nom de l'action 
     */
    public Action(String n) {
        this.name = n;
    }

    /**
     * @return le nom de l'action 
     */
    public String getName(){
        return this.name;
    }

    /**
     * Ca évitera les doublons de temps en temps
     *
     */
    
    @Override
	public boolean equals(Object o) {
		if (o != null && getClass() == o.getClass()) {
            Action other = (Action) o;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
	public String toString() {
        return this.name; 
    }

    /**
     * Pour comparer les actions 
     * Quand il s'agit de les ordonner
     * @param other l'autre instance de action 
     */
    
    @Override
    public int compareTo(Action other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Pour cloner les actions 
     * 
     * @return nouvelle instance de action 
     */
    
    @Override
    public Action clone() {
        return new Action(this.name);
    }
        
}
