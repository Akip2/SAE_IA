package ia.problemes;

import java.util.Arrays;
import java.util.ArrayList;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.common.ArgParse;
import ia.framework.jeux.GameState;

/**
 * Représente un état du jeux du morpion 
 *
 */

public class TicTacToeState extends MnkGameState {

    public TicTacToeState() {
        super(3,3,3);
    }
    
    public TicTacToeState cloneState() {
        TicTacToeState new_s = new TicTacToeState();
        new_s.board = this.board.clone();
        new_s.player_to_move = player_to_move;
        new_s.game_value = game_value;
        if(this.last_action != null)
            new_s.last_action = this.last_action.clone();
        for (Pair p: this.winning_move)
            new_s.winning_move.add(p.clone());
        return new_s;
	}


    /**
     * {@inheritDoc} 
     * 
     * <p>Une fonction d'évaluation simple pour le morpion.
     * La valeur = le nombre de lignes possibles pour X moins
     * le nombre de lignes possibles pour O</p>
     *
     * </p>Une valeur nulle indique que le jeux est équilibré, une valeur positif
     * indique une situation favorable pour X et inversement pour une valeur
     * négative.</p> 
     *
     * @return la valeur du jeu 
     **/
    protected double evaluationFunction(){
        
        int pos_x = this.possibleLines(X);
        int pos_o = this.possibleLines(O);

        double value = pos_x-pos_o;

        // System.out.println("Possibilities: X = "+pos_x+", O = "+pos_o+
        //". Value = "+value);
        return value;
    }
    
    // API privée 

    // compte le nombre de lignes possibles pour player 
    private int possibleLines(int player){
       
        return this.possibleVerticalLines(player) +
            this.possibleHorizontalLines(player) +
            this.possibleDiagonalLines(player) ; 

    }
    
    
    // compte le nombre de lignes verticales possibles pour player 
    private int possibleVerticalLines(int player){
        
        // une ligne possible pour player est un ligne : 
        // * vide
        // * avec une seule pièce de player
        // * avec deux pièces de player
        // Si la ligne contient une pièce de l'adversaire
        // elle n'est pas possible
        
        int res = 0;
        for(int c=0; c<this.cols; c++)
            for(int r=0; r<=this.rows-this.streak; r++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r+k,c) == this.otherPlayer(player) ) {
                        counter = 0;
                        break; // plus besoin de continuer
                    }
                    else if( this.getValueAt(r+k,c) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++; // une ligne possible de plus
            }

        //System.out.println("Possible vertical for "+ (char) player+ " : "+res);
        return res;
    }

    // compte le nombre de lignes horizontales possibles pour player 
    private int possibleHorizontalLines(int player){
        
        // même raisonnement que pour les lignes verticales 

        int res = 0;
        for(int r=0; r<this.rows;r++)
            for(int c=0; c<=this.cols-this.streak; c++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r,c+k) == this.otherPlayer(player) ) {
                        counter = 0;
                        break; // plus besoin de continuer
                    }
                    else if( this.getValueAt(r,c+k) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++; // une ligne possible de plus
            }
        //System.out.println("Possible horizontal for "+ (char) player+ " : "+res);
        return res;
    }

    // compte le nombre de lignes diagonales possibles pour player 
    private int possibleDiagonalLines(int player){
        
        // même raisonnement que pour les lignes verticales 
        
        int res = 0;

        // 45 deg
        for(int c=0; c<=this.cols-this.streak; c++)
            for(int r=0; r<=this.rows-this.streak; r++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r+k,c+k) == this.otherPlayer(player) ) {
                        counter = 0;
                        break; // plus besoin de continuer
                    }
                    else if( this.getValueAt(r+k,c+k) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++; // une ligne possible de plus
            }

        // -45 deg
        for(int c=0; c<=this.cols-this.streak; c++)
            for(int r=this.streak-1; r<this.rows; r++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r-k,c+k) == this.otherPlayer(player) ) {
                        counter = 0;
                        break; // plus besoin de continuer
                    }
                    else if( this.getValueAt(r-k,c+k) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++; // une ligne possible de plus
            }
        //System.out.println("Possible diagonal for "+ (char) player+ " : "+res);
        return res;
    }

   
    
}
