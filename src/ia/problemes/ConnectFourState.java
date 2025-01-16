package ia.problemes;

import java.util.Random;
import java.util.Arrays;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.common.Misc;
import ia.framework.jeux.GameState;

public class ConnectFourState extends AbstractMnkGameState {
    
	public ConnectFourState() {
        super(6, 7, 4);
    }

    public ConnectFourState cloneState() {
		ConnectFourState new_s = new ConnectFourState();
        new_s.board = this.board.clone();
        new_s.player_to_move = this.player_to_move;
        new_s.game_value = this.game_value;
        if( this.last_action != null )
            new_s.last_action = this.last_action.clone();
        for (Pair p: this.winning_move)
            new_s.winning_move.add(p.clone());
        
        return new_s;
    }
 


    /**
     * 
     * {@inheritDoc}
     * <p>Surchargé car les actions ne sont pas standards<p>
     * @param idx indice de la colonne
     * @return vrai si encore de l'espace dans cette colonne
     */ 
    public boolean isLegal(int idx) {
        return this.getFreeRow(idx) != -1;
    }


    /**
     * {@inheritDoc}
     * <p>Pour ce jeu on choisi la colonne, et la pièce tombe.</p>
     * 
     * @param col l'index de la case
     */
    
	public void play(int col) {
                
        // récupérer la première ligne vide a cette colonne 
        int row = this.getFreeRow(col);
        
        // poser la pièce
        this.board[getPositionAt(row,col)] = (char) player_to_move;

        // enregistrer le coup (pour l'affichage) 
        this.last_action = new Pair (row, col);
        
        // Mettre a jour la valeur du jeu 
        this.updateGameValue();
        
        // change de joueur
        this.player_to_move = ( this.player_to_move == X ? O : X); 
    }

    /**
     * {@inheritDoc} 
     * Un fonction d'évaluation trop bête 
     *
     * 
     * @return la valeur du jeux pour le joueur courant 
     **/
    public double evaluationFunction(){
        return Double.NaN;
    }
            
    // l'API privée 
        
	/**
	 * Retourne la premier case vide de la colonne col 
     * -1 si c'est full
     */
	private int getFreeRow(int col) {
        if (col>=0 && col <this.cols)
            for (int r=0; r<this.rows; r++)
                if(this.getValueAt(r, col) == EMPTY)
                    return r;
        return -1;
    }
}
