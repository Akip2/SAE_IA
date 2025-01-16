package ia.framework.common;

/**
 * Un état générique une abstraction pour problème et jeux 
 */

public abstract class State implements Cloneable {

    /**
     * Crée un clone de l'état
     * @return une copie de l'état
     */
    
    protected  abstract State cloneState();

    /** 
     * Vérifier l'égalité avec un autre état
     * @param o Un autre état 
     * @return Vrai si c'est les même 
     */
    
    protected abstract boolean equalsState(State o);

    /** 
     * Calculer un haché de l'état. Utile pour les comparaison  
     * @return le haché
     */
    protected abstract int hashState();

    
    @Override
    public State clone(){
        return cloneState();
    }
    
    @Override
    public boolean equals(Object o){
        if (o != null && getClass() == o.getClass()) {
            State s = (State) o;
            return equalsState(s);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hashState() ;
    }
}
