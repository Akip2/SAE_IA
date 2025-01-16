package ia.problemes;

import java.util.ArrayList;
import java.util.Scanner;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;

/**
 * Classe qui represente le jeux connect 4 
 */
public class ConnectFour extends AbstractMnkGame {
    
    public ConnectFour() {
        super(6, 7, 4);
            
        // Il faut reconstruire le tableaux d'actions  
        // une action par colonne
        // laisser tomber une pièce 
        
        ACTIONS = new Action[this.cols];
        for(int i=0; i<this.cols; i++)
            ACTIONS[i] = new Action(Integer.toString(i));
    }

    /**
     * {@inheritDoc}
     * <p>Crée une grille vide</p>
     */
    public GameState init(){
        ConnectFourState s = new ConnectFourState();
        s.updateGameValue();
        return s;
    }

    /**
     * {@inheritDoc}
     *
     * <p>L'action est définie par la colonne</p> 
     * 
     */
    public Action getHumanMove(GameState state){
        
        Scanner in = new Scanner(System.in);
       
        while( true ){
            System.out.print("A toi de jouer [colonne] > ");
            
            String  line = in.nextLine();    
            String[] strs = line.trim().split("\\s+");
                        
            try {
                int x = Integer.parseInt(strs[0]);
                if (x >= 0 && x < this.cols &&
                    ((ConnectFourState) state).isLegal(x) )
                    return ACTIONS[x];
                throw new Exception();
                
            } catch (Exception e ) {
                System.out.println("Invalide !");
            }
        }
    }
    
}
