package ia.framework.common;

import java.util.ArrayList;
import java.util.Set;
/**
 * Représente une abstraction pour un problème ou un jeux
 *
 */

public abstract class BaseProblem {


    /**
     *  <p>Une petite description du problème/jeu</p>
     */
    
    protected String desc = "Problème générique";
    
    /**
     *  <p>La liste de toutes les actions
     *  les classes concrètes doivent le renseigner </p>
     *   Voir {@link ia.problemes.Vacuum} ou {@link ia.problemes.EightPuzzle}
     */
    
    protected static Action[] ACTIONS = null;
       

    /**
     * Retourner les actions possibles un état
     * @param s Un état 
     * @return Les actions possibles (pas forcement toutes) depuis s 
     */
    public abstract ArrayList<Action> getActions(State s);

    /**
     * Exécuter une action dans un état 
     * @param s Un état 
     * @param a Une action
     * @return L'état résultat de faire l'action a dans s
     */
    public abstract State doAction(State s, Action a);


    /**
     * un getter pour la description du problème 
     * @return retourne la description du problème
     */
    public String getDescription(){
        return desc;
    }

    /**
     * encode le graphe des états en format DOT.
     * Ceci n'est possible que pour les graphe des problème
     * simple i.e. (instance de Problem)
     *
     * @param s l'état initial 
     * @param sol la suite d'actions de la solution (null si pas de solution)
     * @return le graphe encodé 
     */ 
    public String toDot(State s, ArrayList<Action> sol, Set<State> vis){
        return "";
    }

}
