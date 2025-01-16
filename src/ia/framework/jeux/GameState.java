package ia.framework.jeux;

import ia.framework.common.State;
import ia.framework.common.Action;

/**
 * Represente un etat d'un jeux
 *
 * Un etat (State) avec une valeur
 */
  
public abstract class GameState extends State {

    // les valeur possible du jeux 
    public static final double P1_WIN = Double.POSITIVE_INFINITY;
    public static final double P2_WIN = Double.NEGATIVE_INFINITY;
    public static final double DRAW = 0; // null
        
    protected double game_value; // la valeur du jeux 
    protected int player_to_move; // le joueur a qui c'est le tour 
    
    /** 
     * La valeur de l'état.
     *
     * <p>
     *  Valeur des position finales
     *  <ul>
     *  <li> Double.POSITIVE_INFINITY : si le joueur 1 a gagné </li>
     *  <li> 0 : si match nul </li>
     *  <li> Double.NEGATIVE_INFINITY : si joueur 2 a gagné </li>
     *  </ul>
     *  Le reste du temps le résultat de la fonction de valeur
     *  (Si le jeux l'implémentes)
     * </p>     
     * @return la valeur de la partie
     */
    public double getGameValue (){
        return game_value;
	}
 
    /**
     * Met a jour la valeur du jeu
     * Gagnant, Nul, ou une valeur calculer par une fonction de
     * valeur 
     *  
     */
    protected abstract void updateGameValue();

    
    /**
     * Pour savoir si l'état est final (fin de partie)
     *
     * @return vrai si c'est un état final 
     */
    public abstract boolean isFinalState();
    
    
    /**
     * Retourne a qui de jouer
     *
     * @return le jouer a jouer
     */
    public int getPlayerToMove() {
		return player_to_move;
	}

    /**
     * {@inheritDoc}
     */
    public boolean equalsState(State o) {
        GameState other = (GameState) o;
        return game_value==other.getGameValue() &&
            player_to_move==other.getPlayerToMove(); 
    }
}
