import ia.framework.common.ArgParse;

import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.GameEngine;
import ia.framework.jeux.Player;


import ia.problemes.*; 

/**
 * Lance un une partie de jeux donné et affiche le resultat
 */
public class LancerJeux {

    public static void main(String[] args){

        // fixer le message d'aide
        ArgParse.setUsage
            ("Utilisation :\n\n"
             + "java LancerJeux [-game jeux] "
             + "[-p1 joueur1] [-p2 joueur1] "
             + "[-d entier] "
             + "[-s entier] "
             + "[-k entier] "
             + "[-v] [-h]\n\n"
             + "-game : Le nom du jeux {tictactoe, connect4, gomoku, mnk}. Par défautl tictactoe\n"
             + "-p1/2 : L'agorithme joueur {random, human, minmax, alphabeta}. Par défaut rnd pour les deux\n"
             + "-d    : La profondeur max pour minmax ou alphabeta. Par défaut sans limite\n"
             + "-s    : La taille du jeux Mnk sXs. Par défaut 5x5\n"
             + "-k    : La taille d'une ligne gagnante au Mnk. Par défaut s-1\n"
             + "-v    : Rendre bavard (mettre à la fin)\n"
             + "-h    : Afficher ceci (mettre à la fin)\n"
             
             );
        
        // récupérer les option de la ligne de commande
        String game_name = ArgParse.getGameFromCmd(args);
        String p1_type   = ArgParse.getPlayer1FromCmd(args);
        String p2_type   = ArgParse.getPlayer2FromCmd(args);
 
        // créer un jeux, des joueurs et le moteur de jeux
        Game game = ArgParse.makeGame(game_name, args);
        Player p1 = ArgParse.makePlayer(p1_type, game, true, args);
        Player p2 = ArgParse.makePlayer(p2_type, game, false, args);
        GameEngine game_engine = new GameEngine(game, p1, p2);

        // on joue jusqu'à la fin
        long startTime = System.currentTimeMillis();
        GameState end_game = game_engine.gameLoop();
        long estimatedTime = System.currentTimeMillis() - startTime;

        // Partie fini 
        Player winner = game_engine.getWinner(end_game);
        if(winner != null){
            System.out.print("Le joueur "
                             +(game_engine.getEndGameValue(end_game) == GameState.P1_WIN ? 1: 2)
                             +" ("+ winner.getName()+") a gagné, après "
                             +game_engine.getTotalMoves()
                             +" coups. ");
        } else
            System.out.print("Match nul. ");
        System.out.println("La partie à durée "+estimatedTime/1000.+" sec.");     
        System.out.println("Le Joueur 1 a exploré "+ p1.getStateCounter() +" états");
        System.out.println("Le Joueur 2 a exploré "+ p2.getStateCounter() +" états");
        
        if (game_engine.getEndGameValue(end_game) == GameState.P1_WIN)
            System.exit(101);
        if (game_engine.getEndGameValue(end_game) == GameState.P2_WIN)
            System.exit(102);
        if (game_engine.getEndGameValue(end_game) == GameState.DRAW)
            System.exit(100);
                
    }
}
