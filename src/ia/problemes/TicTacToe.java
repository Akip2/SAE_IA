package ia.problemes;

import java.util.ArrayList;
import java.util.Scanner;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;

/**
 * Classe qui represente le jeu du morpion 
 */
public class TicTacToe extends MnkGame {

    public TicTacToe() {
        super(3,3,3);
    }

    /**
     * {@inheritDoc}
     * <p>Crée une grille de morpion vide</p>
     */
    public GameState init(){
        TicTacToeState s = new TicTacToeState();
        s.updateGameValue();
        return s;
    }
    
    
    
}
