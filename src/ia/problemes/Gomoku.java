package ia.problemes;

import java.util.Arrays;
import java.util.ArrayList;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.GameState;

/**
 * Représente un état du jeu du Gomoku 
 *
 */

public class Gomoku extends AbstractMnkGame {
    

    public Gomoku() {
        super(15,15,5);
    }

    /**
     * {@inheritDoc}
     * <p>Crée une grille vide</p>
     */
    public GameState init(){
        GomokuState s = new GomokuState();
        s.updateGameValue();
        return s;
    }

}
