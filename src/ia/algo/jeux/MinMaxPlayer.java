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
        Action move;
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
            System.out.println(game.getActions(state).toString());
            return new ActionValuePair(game.getActions(state).getFirst(), state.getGameValue());
        }

        double valueMax = Double.MIN_VALUE;
        Action actionMax = null;

        ArrayList<Action> actions = game.getActions(state);
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState);

            if(pair.getValue() >= valueMax){
                valueMax = pair.getValue();
                actionMax = action;
            }
        }

        return new ActionValuePair(actionMax, valueMax);
    }

    public ActionValuePair minValeur(GameState state) {
        incStateCounter();

        if(state.isFinalState()){
            System.out.println(game.getActions(state).toString());
            return new ActionValuePair(game.getActions(state).getFirst(), state.getGameValue());
        }

        double valueMin = Double.MAX_VALUE;
        Action actionMin = null;

        ArrayList<Action> actions = game.getActions(state);
        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState);

            if(pair.getValue() <= valueMin){
                valueMin = pair.getValue();
                actionMin = action;
            }
        }

        return new ActionValuePair(actionMin, valueMin);
    }
}
