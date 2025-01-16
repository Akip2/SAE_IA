package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.common.ActionValuePair;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     */
    public MinMaxPlayer(Game g, boolean player_one) {
        super(g, player_one);
    }

    @Override
    public Action getMove(GameState state) {
        int player = state.getPlayerToMove();

        Action move = null;
        ActionValuePair pair;

        if(player == PLAYER1){
            pair = minValeur(state);
        }
        else {
            pair = maxValeur(state);
        }

        move = pair.getAction();

        return move;
    }

    public ActionValuePair maxValeur(GameState state) {
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }
        else {
            ActionValuePair maxPair = new ActionValuePair(null, Double.NEGATIVE_INFINITY);
            ArrayList<Action> actions = game.getActions(state);

            for(Action action : actions){
                State nextState = game.doAction(state, action);
                ActionValuePair pair = minValeur((GameState) nextState);

                if(pair.getValue() > maxPair.getValue()){
                    maxPair = new ActionValuePair(action, pair.getValue());
                }
            }

            return maxPair;
        }
    }

    public ActionValuePair minValeur(GameState state) {
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }
        else {
            ActionValuePair minPair = new ActionValuePair(null, Double.POSITIVE_INFINITY);
            ArrayList<Action> actions = game.getActions(state);

            for(Action action : actions){
                State nextState = game.doAction(state, action);
                ActionValuePair pair = maxValeur((GameState) nextState);

                if(pair.getValue() < minPair.getValue()){
                    minPair = new ActionValuePair(action, pair.getValue());
                }
            }

            return minPair;
        }
    }
}
