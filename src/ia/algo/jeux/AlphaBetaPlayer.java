package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.common.ActionValuePair;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

import java.util.ArrayList;

public class AlphaBetaPlayer extends Player {
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     */
    public AlphaBetaPlayer(Game g, boolean player_one) {
        super(g, player_one);
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair pair;

        if(player == PLAYER1){
            pair = maxValeur(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
        else {
            pair = minValeur(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        return pair.getAction();
    }

    public ActionValuePair maxValeur(GameState state, double alpha, double beta) {
        incStateCounter();
        if(game.endOfGame(state)){
            return new ActionValuePair(null, state.getGameValue());
        }

        double maxValue = Double.NEGATIVE_INFINITY;
        Action maxAction = null;

        ArrayList<Action> actions = game.getActions(state);
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState, alpha, beta);

            if(pair.getValue() >= maxValue) {
                maxValue = pair.getValue();

                if(maxValue > alpha) {
                    maxAction = action;
                    alpha = maxValue;
                }
            }
            if(maxValue >= beta) {
                return new ActionValuePair(maxAction, maxValue);
            }
        }

        return new ActionValuePair(maxAction, maxValue);

    }

    public ActionValuePair minValeur(GameState state, double alpha, double beta) {
        incStateCounter();
        if(game.endOfGame(state)){
            return new ActionValuePair(null, state.getGameValue());
        }

        double minValue = Double.POSITIVE_INFINITY;
        Action minAction = null;

        ArrayList<Action> actions = game.getActions(state);
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState, alpha, beta);

            if(pair.getValue() <= minValue){
                minValue = pair.getValue();

                if(minValue < beta){
                    minAction = action;
                    beta = minValue;
                }
            }
            if(minValue <= alpha){
                return new ActionValuePair(minAction, minValue);
            }
        }
        return new ActionValuePair(minAction, minValue);
    }

}
