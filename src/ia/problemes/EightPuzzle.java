package ia.problemes;

import java.util.ArrayList;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchProblem;

/**
 * Représente le problème du jeu de taquin
 *
 */
public class EightPuzzle extends SearchProblem {
    
    // Les actions possible pour le carré vide
       
    public static final Action UP    = new Action("Up");
    public static final Action LEFT  = new Action("Left");
    public static final Action DOWN  = new Action("Down");
    public static final Action RIGHT = new Action("Right");

    /**
     * L'état but (le carré vide en bas à droite)
     */
    
    public static final EightPuzzleState GOAL_STATE = 
        new EightPuzzleState(new int[] {1,2,3,4,5,6,7,8,0} );

    /**
     * Crée une instance du problème du taquin
     *
     */
    public EightPuzzle(){

        // Description 
        this.desc = "Le jeux du taquin du taquin version 3x3";
        
        // La liste des actions possibles  
        ACTIONS = new Action[] { UP, LEFT, DOWN, RIGHT };
    }


    /**
     * {@inheritDoc}
     * <p>Chaque mouvement coûte 1</p>
     */
    public double getActionCost(State s, Action a){
        return 1.0;
    }

    /**
     * {@inheritDoc}
     * <p>Retourne uniquement celle qui sont possibles</p>
     */
    public ArrayList<Action> getActions(State s){
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Action a : ACTIONS)
            if( ((EightPuzzleState) s).isLegal(a))
                actions.add(a);
        return actions;
    }
    
    public State doAction(State s, Action a){

        // on copie l'état courent et on le modifie
        EightPuzzleState b = (EightPuzzleState) s.clone();
        if (a == LEFT)
            b.moveGapLeft();
		else if (a == RIGHT)
            b.moveGapRight();
		else if (a == UP)
			b.moveGapUp();
		else if (a == DOWN)
            b.moveGapDown();
        else
            throw new IllegalArgumentException("Invalid"+a);
        return b;
    }
        
    public boolean isGoalState(State s){
        return ((EightPuzzleState)s).equals(GOAL_STATE);
    }
}
