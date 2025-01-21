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
        Action move = null;
        ActionValuePair pair;

        if(player == PLAYER1){
            System.out.println("NUM 1");
            pair = maxValeur(state);
        }
        else {
            pair = minValeur(state);
        }
        move = pair.getAction();

        return move;
    }

    public ActionValuePair maxValeur(GameState state) {
        incStateCounter();
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }
        ActionValuePair maxPair = new ActionValuePair(null, Double.NEGATIVE_INFINITY);
        ArrayList<Action> actions = game.getActions(state);

        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState);

            if(pair.getValue() >= maxPair.getValue()){
                maxPair = new ActionValuePair(action, pair.getValue());
            }
        }

        return maxPair;
    }

    public ActionValuePair minValeur(GameState state) {
        incStateCounter();
        if(state.isFinalState()){
            return new ActionValuePair(null, state.getGameValue());
        }
        ActionValuePair minPair = new ActionValuePair(null, Double.POSITIVE_INFINITY);
        ArrayList<Action> actions = game.getActions(state);

        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState);

            if(pair.getValue() <= minPair.getValue()){
                minPair = new ActionValuePair(action, pair.getValue());
            }
        }

        return minPair;
    }
}
