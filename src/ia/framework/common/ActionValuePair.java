package ia.framework.common;

/**
 * Représente un couple (action, valeur)
 */

public class ActionValuePair {

    /**
     * Contient une Action et une valeur double 
     */
    
    private final Action action ;
    private final double value;

    
    /**
     * Crée une paire action valeur 
     * @param a l'action
     * @param v sa valeur
     */
    public ActionValuePair(Action a, double v) {
        value = v;
        action = a;
    }
    
    /**
     * Getter : pour action 
     * @return l'action  
     */
    public Action getAction(){
        return action;
    }

    /**
     * getter : pour valeur 
     * @return la valeur de l'action 
     */
    public double getValue(){
        return value;
    }

    
    /**
     * Pour dupliquer
     * @return une copie profonde de this
     */
    
    @Override
    public ActionValuePair clone() {
        if(this.action != null)
            return new ActionValuePair(this.action.clone(), this.value);
        return new ActionValuePair(this.action, this.value);
            
    }
    
    /**
     * Ca évitera les doublons de temps en temps
     * @param o Une autre paire
     * @return Vrai si égale à nous   
     */
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionValuePair)) return false;
        ActionValuePair other = (ActionValuePair) o;
        return value == other.value &&
            action.equals(other.action);
    }
    /**
     * Les paires sont utilisé comme clé de hasmap
     * par exemple. Elle doivent être hachée
     * @return le haché 
     */
    
    @Override
    public int hashCode() {
        int result = Double.hashCode(value);
        result = 31 * result + action.hashCode();
        return result;
    }

    public String toString(){
        return "<"+(action !=null ? action.getName() : "NULL")+","+value+">";
    }
}
