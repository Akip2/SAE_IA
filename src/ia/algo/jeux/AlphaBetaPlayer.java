package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.common.ActionValuePair;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;
import java.util.ArrayList;

public class AlphaBetaPlayer extends Player {
    private int maxDepth;

    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     * @param maxDepth   profondeur maximale pour l'algorithme
     */
    public AlphaBetaPlayer(Game g, boolean player_one, int maxDepth) {
        super(g, player_one);
        this.maxDepth = maxDepth;
        name = "alphabeta";
    }

    @Override
    public Action getMove(GameState state) {
        if(player==PLAYER1) {
            return maxValeur(state, -Double.MAX_VALUE, Double.MAX_VALUE, 0).getAction();
        }
        else {
            return minValeur(state, -Double.MAX_VALUE, Double.MAX_VALUE, 0).getAction();
        }
    }


    public ActionValuePair maxValeur(GameState state, double alpha, double beta, int depth) {
        incStateCounter();

        if (game.endOfGame(state) || depth>=maxDepth) {
            return new ActionValuePair(null, state.getGameValue());
        }

        double maxValue = -Double.MAX_VALUE;
        Action bestAction = null;

        ArrayList<Action> actions = game.getActions(state);
        for (Action action : actions) {
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState, alpha, beta, depth + 1);

            if (pair.getValue() > maxValue) {
                maxValue = pair.getValue();
                bestAction = action;
            }

            if (maxValue > alpha) {
                alpha = maxValue;
            }

            if (maxValue >= beta) {
                return new ActionValuePair(bestAction, maxValue);
            }
        }

        return new ActionValuePair(bestAction, maxValue);
    }

    public ActionValuePair minValeur(GameState state, double alpha, double beta, int depth) {
        incStateCounter();

        if (game.endOfGame(state) || depth>=maxDepth) {
            return new ActionValuePair(null, state.getGameValue());
        }

        double minValue = Double.MAX_VALUE;
        Action bestAction = null;

        ArrayList<Action> actions = game.getActions(state);
        for (Action action : actions) {
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState, alpha, beta, depth + 1);

            if (pair.getValue() < minValue) {
                minValue = pair.getValue();
                bestAction = action;
            }

            if (minValue < beta) {
                beta = minValue;
            }

            if (minValue <= alpha) {
                return new ActionValuePair(bestAction, minValue);
            }
        }

        return new ActionValuePair(bestAction, minValue);
    }
}
