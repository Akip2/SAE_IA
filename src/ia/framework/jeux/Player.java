package ia.framework.jeux;


import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;

/**
 * Définie un joueur
 *
 */

public abstract class Player {

    public static final int PLAYER1 = 0; // les deux joueurs
    public static final int PLAYER2 = 1; 

    protected String name = null; // le nom de l'algo
    
    protected int player; // le joueur 1 ou 2 
    
    protected Game game; // l'instance du jeux

    protected int state_count ; // compteur d'états visités
    
    /**
     * Represente un joueur
     *
     * @param g l'instance du jeux
     * @param player_one si joueur 1
     */    
    public Player(Game g, boolean player_one){
        this.game = g;
        this.player = PLAYER1;
        if (!player_one)
            this.player = PLAYER2;
        this.resetStateCounter();
    }

    /**
     * un getter pour le nom
     *
     * @return le nom du joueur (algorithme)
     */
    public String getName(){
        return this.name;
    }

    /**
     * un getter pour le compteur d'états visités
     *
     * @return le nombre d'état
     */
    public int getStateCounter(){
        return this.state_count ;
    }
    
    /**
     * Incrémente le nombre d'état visité
     *
     */
    protected void incStateCounter(){
        this.state_count++;
    }

    /**
     * Remet le compteur du nombre d'état visité à zéro
     *
     */
    protected void resetStateCounter(){
        this.state_count = 0;
    }
    
    /* 
     * Récupère le coup pour ce joueur. Ce coup doit être légal 
     *
     * @param l'état du jeu
     * @return le coup (Action) choisi 
     */
    public abstract Action getMove(GameState state);


    
    
}

