package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.Problem;

/**
 * Représente le problème de notre robot aspirateur
 * 
 */
public class Vacuum extends Problem {

    public static VacuumState LDD = new VacuumState(0,new boolean[]{true,  true});
    public static VacuumState RDD = new VacuumState(1,new boolean[]{true,  true});
    public static VacuumState L_D = new VacuumState(0,new boolean[]{false, true});
    public static VacuumState R_D = new VacuumState(1,new boolean[]{false, true});
    public static VacuumState LD_ = new VacuumState(0,new boolean[]{true,  false});
    public static VacuumState RD_ = new VacuumState(1,new boolean[]{true,  false});
    public static VacuumState L__ = new VacuumState(0,new boolean[]{false, false});
    public static VacuumState R__ = new VacuumState(1,new boolean[]{false, false});
    

    
    public Vacuum() {

        desc = "Problème de l'aspirateur (vacuum)"; 
        
        
        // L'ensemble des états 

        STATES = new State[] { LDD, RDD, L_D, R_D, LD_, RD_, L__, R__ };
        
        // L'ensemble des actions
        
        Action GO_LEFT = new Action("Go left");
        Action GO_RIGHT = new Action("Go right");
        Action SUCK = new Action("Suck");

        ACTIONS = new Action[] { GO_LEFT, GO_RIGHT, SUCK };

        // Les transitions possibles 
        TRANSITIONS.addTransition(LDD, GO_LEFT,  LDD, 1);
        TRANSITIONS.addTransition(LDD, GO_RIGHT, RDD, 1);
        TRANSITIONS.addTransition(LDD, SUCK,     L_D, 1);
        
        TRANSITIONS.addTransition(RDD, GO_LEFT,  LDD, 1);
        TRANSITIONS.addTransition(RDD, GO_RIGHT, RDD, 1);
        TRANSITIONS.addTransition(RDD, SUCK,     RD_, 1);
        
        TRANSITIONS.addTransition(L_D, GO_LEFT,  L_D, 1);
        TRANSITIONS.addTransition(L_D, GO_RIGHT, R_D, 1);
        TRANSITIONS.addTransition(L_D, SUCK,     L_D, 1);
        
        TRANSITIONS.addTransition(LD_, GO_LEFT,  LD_, 1);
        TRANSITIONS.addTransition(LD_, GO_RIGHT, RD_, 1);
        TRANSITIONS.addTransition(LD_, SUCK,     L__, 1);
        
        TRANSITIONS.addTransition(RD_, GO_LEFT,  LD_, 1);
        TRANSITIONS.addTransition(RD_, GO_RIGHT, RD_, 1);
        TRANSITIONS.addTransition(RD_, SUCK,     RD_, 1);
        
        TRANSITIONS.addTransition(R_D, GO_LEFT,  L_D, 1);
        TRANSITIONS.addTransition(R_D, GO_RIGHT, R_D, 1);
        TRANSITIONS.addTransition(R_D, SUCK,     R__, 1);
        
        TRANSITIONS.addTransition(R__, GO_LEFT,  L__, 1);
        TRANSITIONS.addTransition(R__, GO_RIGHT, R__, 1);
        TRANSITIONS.addTransition(R__, SUCK,     R__, 1);
        
        TRANSITIONS.addTransition(L__, GO_LEFT,  L__, 1);
        TRANSITIONS.addTransition(L__, GO_RIGHT, R__, 1);
        TRANSITIONS.addTransition(L__, SUCK,     L__, 1);

        
        
    }

    public boolean isGoalState(State s){
        return s.equals(R__) || s.equals(L__) ;
    }


    
    
}
