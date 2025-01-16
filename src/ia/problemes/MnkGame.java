package ia.problemes;

import java.util.ArrayList;
import java.util.Scanner;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;

/**
 * Représente un jeux du m,n,k (cf. https://en.wikipedia.org/wiki/M,n,k-game)
 *
 */

public class MnkGame extends AbstractMnkGame {
        
    public MnkGame(int r, int c, int s) {
        super(r,c,s);
    }

    /* {@inheritDoc}
     * <p>Crée une grille vide</p>
     */
    public GameState init(){
        MnkGameState s = new MnkGameState(this.rows, this.cols, this.streak);
        s.updateGameValue();
        return s;
    }
    
}
