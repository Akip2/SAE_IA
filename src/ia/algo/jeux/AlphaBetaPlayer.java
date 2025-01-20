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
        Action move = null;
        ActionValuePair pair;

        if(player == PLAYER1){
            pair = maxValeur(state, Double.MIN_VALUE, Double.MAX_VALUE);
        }
        else {
            pair = minValeur(state, Double.MIN_VALUE, Double.MAX_VALUE);
        }

        move = pair.getAction();

        return move;
    }

    public ActionValuePair maxValeur(GameState state, double alpha, double beta) {
        incStateCounter();
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }

        double maxValue = Double.MIN_VALUE;
        Action maxAction = null;

        ArrayList<Action> actions = game.getActions(state);
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState, alpha, beta);

            if(pair.getValue() >= maxValue) {
                maxValue = pair.getValue();
                maxAction = action;

                if(maxValue > alpha) {
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
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }
        ;
        double minValue = Double.MAX_VALUE;
        Action minAction = null;

        ArrayList<Action> actions = game.getActions(state);
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState, alpha, beta);

            if(pair.getValue() <= minValue){
                minValue = pair.getValue();
                minAction = action;

                if(minValue < beta){
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
