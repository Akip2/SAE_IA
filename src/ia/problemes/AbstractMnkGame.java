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

public abstract class AbstractMnkGame extends Game {
    
    // taille du jeux
    protected int cols;
    protected int rows;

    // longueur des lignes gagnante
    protected int streak;
    
    public AbstractMnkGame(int r, int c, int s) {
        this.cols = c;
        this.rows = r;
        this.streak = s;
        
        // Construire le tableaux d'actions  
        // une action par ligne x colonne
                
        ACTIONS = new Action[r*c];
        for(int i=0; i<r*c; i++)
            ACTIONS[i] = new Action(Integer.toString(i));
    }
    
    /**
     * {@inheritDoc}
     * <p>retourne les actions possibles en fonction 
     * du joueur et de l'état du jeux</p>
     */
    public ArrayList<Action> getActions(State s){
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Action a : ACTIONS){
            int action_idx = Integer.parseInt(a.getName());
            AbstractMnkGameState game_state = (AbstractMnkGameState) s;
            if( game_state.isLegal(action_idx) )
                actions.add(a);
        }
        return actions;
    }
    
    /**
    * {@inheritDoc}
    *
    * <p>Joue le coup a dans l'état et 
    * retourne la nouvelle grille après l'action</p>
    */
    public State doAction(State s, Action a){
        AbstractMnkGameState new_s = (AbstractMnkGameState) s.clone();
        int action_idx = Integer.parseInt(a.getName());
        new_s.play(action_idx);
        return new_s;
    }
    
    /**
     * {@inheritDoc}
     * <p>Fin dès qu'il y a une ligne ou plus de place de jeux</p>
     *
     */
     public boolean endOfGame(GameState s){
         return s.isFinalState();
     }
    
    /**
     * {@inheritDoc}
     *
     * <p>L'action est définie par les coordonnées (ligne colonne)
     * de la case.</p> 
     * 
     */
    public Action getHumanMove(GameState state){
        
        Scanner in = new Scanner(System.in);
       
        while( true ){
            System.out.print("A toi de jouer [ligne colonne] > ");
            
            String  line = in.nextLine();    
            String[] strs = line.trim().split("\\s+");

            try {
                int y = Integer.parseInt(strs[0]);
                int x = Integer.parseInt(strs[1]);
                if (y >= 0 && y < this.rows && x >= 0 && x < this.cols &&
                    ((AbstractMnkGameState) state).isLegal(y*this.cols+x) )
                    return ACTIONS[y*this.cols+x];
                throw new Exception(); 
                    
            } catch (Exception e ) {
                System.out.println("Invalide !");
            }
        }
    }
    
}
