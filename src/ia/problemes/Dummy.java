package ia.problemes;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.lang.RuntimeException;

import ia.framework.common.Misc;
import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.common.ArgParse;
import ia.framework.recherche.Problem;

/**
 * Représente un problème générique
 * <p>Le problème est un graphe aléatoire de taille
 * spécifiée par les constantes de la classe.</p>
 *
 */

public class Dummy extends Problem {

    private Random rng = new Random() ;// génerateur aléatoire 
    private int size; // nombre de noeud du graphe (taille du problème)
    private int links; // facteur de branchement
    private long seed; // la graine aléatoire pour refaire le problème
    
    /**
     * Crée un problème représenter par un graphe aléatoire.
     * Par convention l'état initial est STATES[0] et l'état 
     * but est STATES[size-1]
     * 
     * @param s nombre de noeud dans le graphe
     * @param l facteur de branchement
     * @param g graine aléatoire 
     */

    public Dummy(int s, int l, long g){

        this.size = s;
        this.links = l;
        
        // fixer la graine
        if(g > 0){ // donnée
            this.seed = g;
        } else { // générer
            this.seed = this.rng.nextInt();
        }
        this.rng.setSeed(this.seed);
        
        System.out.println("Instance de Dummy crée avec "+size+
                           " noeuds et un facteur de branchement de "+links+". Graine aléatoire "+seed);
                
        // Créer quelques états et quelques actions
        STATES = new DummyState[size];
        ACTIONS = new Action[size];
        
        // créer un graphe aléatoire
        makeGraph();
    }
   
    /**
     * L'état but est le dernier état générer
     */
    public boolean isGoalState(State s){
        return s.equals(STATES[this.size-1]);
    }

    /**
     * Retourne l'état initial du probleme (le premier)
     * soulève une exception fatal, si l'état n'est pas encore créer 
     * @return l'état initial 
     */
    public static DummyState initialState() {
        if(STATES.length > 0)
            return (DummyState) STATES[0];
        throw new RuntimeException("Etat initial pas encore défini. Créer une instance de Dummy avant.");
    }

    
    // l'API privée 
    private void makeGraph(){

        // TODO : update algo see https://stackoverflow.com/questions/2041517/random-simple-connected-graph-generation-with-given-sparseness
        
        // créer des noeuds aléatoires
        makeNodes();

        // Créer des transitions aléatoires
        makeLinks();
    }
    
    // crée les noeud du graphe sur une grille 2D
    private void makeNodes(){

        // pour éviter les superpositions (enregistre les positions générées)
        HashSet<Point2D> generated = new HashSet<Point2D>();
        
        for(int i=0; i<this.size; i++){
            STATES[i] = new DummyState(i);
            Point2D pos = null;
            do {
                pos = getRandomPosition();
            } while (generated.contains(pos));
            ((DummyState) STATES[i]).setPosition(pos);

            ACTIONS[i] = new Action("Goto "+ i);
        }
        
        // calculer la distance au but (pour l'heuristique distance
        // à vol d'oiseaux)

        Point2D goal = ((DummyState) STATES[this.size-1]).getPosition();
        for(int i=0; i<this.size; i++){
            Point2D pos = ((DummyState) STATES[i]).getPosition();
            ((DummyState) STATES[i]).setDistToGoal(goal.distance(pos));
        }
    }

    // Crée des transitions aléatoires 
    private void makeLinks(){

        // une liste d'indices qu'on mélange
        int [] indexes = new int[this.size-1];
        for(int i=0; i<this.size-1; i++)
            indexes[i] = i;
        shuffleArray(indexes);
        
        // chaque noeud est relié à this.links noeuds choisis aléatoirement 
        int idx = -1;
        for(int i=0; i<this.size-1; i++){
            DummyState s1 = (DummyState) STATES[i];
            
            int l = 0; // prendre l noeuds 
            while ( l<this.links ) {
                idx++;
                if (idx == this.size-1)
                    idx = 0;
                
                DummyState s2 = (DummyState) STATES[indexes[idx]];

                // éviter le boucles
                if(s1.equals(s2))
                    continue;

                // créer le lien 
                Action a2 = ACTIONS[indexes[idx]]; // goto s2

                // calculer le coût (ici distance)
                Point2D p1 = s1.getPosition();
                Point2D p2 = s2.getPosition();
                double cost = p1.distance(p2);
                
                // on met des actions de s1->s2 
                TRANSITIONS.addTransition(s1, a2, s2, cost);
                l++;
            }
        }
        
        // reste a relier le but

        // générer un noeud source ni l'état initial ni le but 
        idx = rng.nextInt(this.size-1);
        while (idx==0)
            idx = rng.nextInt(this.size-1);

        // on le relie au but 
        DummyState s1 = (DummyState) STATES[idx];
        DummyState s2 = (DummyState) STATES[this.size-1];

        Action a2 = ACTIONS[this.size-1]; // goto goal
            
        Point2D p1 = s1.getPosition();
        Point2D p2 = s2.getPosition();
        double cost = p1.distance(p2);
        
        // on met des actions de s1->s2 
        TRANSITIONS.addTransition(s1, a2, s2, cost);
    }

    // Implementing Fisher–Yates shuffle
    private void shuffleArray(int[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rng.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
  
    // générer une postion aléatoire sur une grille
    private Point2D getRandomPosition(){
        int x = rng.nextInt(1024);
        int y = rng.nextInt(1024);
        return new Point2D.Double(x,y);
    }
        
}
