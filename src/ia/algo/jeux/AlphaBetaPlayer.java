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
        ActionValuePair maxPair = new ActionValuePair(null, Double.MIN_VALUE);
        ArrayList<Action> actions = game.getActions(state);

        double maxValue = Double.MIN_VALUE;
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState, alpha, beta);

            double currentValue = pair.getValue();

            if(currentValue >= maxValue) {
                maxValue = currentValue;
                maxPair = new ActionValuePair(action, maxValue);

                if(maxValue > alpha) {
                    alpha = maxValue;
                }
            }

            if(maxValue >= beta) {
                return maxPair;
            }
        }

        return maxPair;

    }

    public ActionValuePair minValeur(GameState state, double alpha, double beta) {
        incStateCounter();
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }
        ActionValuePair minPair = new ActionValuePair(null, Double.MAX_VALUE);
        ArrayList<Action> actions = game.getActions(state);

        double minValue = Double.MAX_VALUE;
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState, alpha, beta);

            double currentValue = pair.getValue();

            if(currentValue <= minValue){
                minValue = currentValue;
                minPair = new ActionValuePair(action, minValue);

                if(minValue < beta){
                    beta = minValue;
                }
            }

            if(minValue <= alpha){
                return minPair;
            }
        }
        return minPair;
    }

}
