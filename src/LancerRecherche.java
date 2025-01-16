import ia.framework.common.ArgParse;
import ia.framework.common.State;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import ia.problemes.*; 

/**
 * Lance un algorithme de recherche  
 * sur un problème donné et affiche le resultat
 */
public class LancerRecherche {

    public static void main(String[] args){

        // fixer le message d'aide
        ArgParse.setUsage
            ("Utilisation :\n\n"
             + "java LancerRecherche [-prob problem] [-algo algoname]"
             + " [-n int] [-k int] [-g int] [-v] [-h]\n\n"
             + "-prob : Le nom du problem {dum, map, vac, puz}. Par défautl vac\n"
             + "-algo : L'agorithme {rnd, bfs, dfs, ucs, gfs, astar}. Par défault rnd\n"
             + "-n    : La taille du problème Dummy. Par défaut 50\n"
             + "-k    : Le facteur de branchement pour Dummy. Par défaut 2\n"
             + "-r    : La graine aléatoire pour Dummy (0 à générer) par defaut 1234\n"
             + "-v    : Rendre bavard (mettre à la fin)\n"
             + "-h    : afficher ceci (mettre à la fin)\n"
             );

        
        // récupérer les option de la ligne de commande
        String prob_name = ArgParse.getProblemFromCmd(args);
        String algo_name = ArgParse.getAlgoFromCmd(args);

        // créer un problem, un état intial et un  algo
        SearchProblem p = ArgParse.makeProblem(prob_name, args);
        State s = ArgParse.makeInitialState(prob_name);
        TreeSearch algo = ArgParse.makeAlgo(algo_name, p, s);

        // afficher quelques info
        if(ArgParse.DEBUG)
            System.out.println(p.getDescription());

        // afficher le graphe quand c'est possible (Instances de Problem)
        if(ArgParse.DEBUG)
            System.out.println(p.toDot(s,
                                       algo.getSolution(),
                                       algo.getVisited()));   
        // résoudre
        long startTime = System.currentTimeMillis();
        boolean solved = algo.solve();
        long estimatedTime = System.currentTimeMillis() - startTime;

        if( solved )
            algo.printSuccess();
        else
            algo.printFailure();
        System.out.println("Temps n'écessaire "+estimatedTime/1000.+" sec.");  

       
    }
}
