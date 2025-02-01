package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.common.ActionValuePair;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {
    private int maxDepth;
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeu
     * @param player_one si joueur 1
     */
    public MinMaxPlayer(Game g, boolean player_one, int maxDepth) {
        super(g, player_one);
        name="minmax";
        this.maxDepth = maxDepth;
    }

    @Override
    public Action getMove(GameState state) {
        if(player == PLAYER1){
            return maxValeur(state, 0).getAction();
        }
        else {
            return minValeur(state, 0).getAction();
        }
    }

    public ActionValuePair maxValeur(GameState state, int depth) {
        incStateCounter();
        if(game.endOfGame(state) || depth>=this.maxDepth){
            return new ActionValuePair(null, state.getGameValue());
        }
        ActionValuePair maxPair = new ActionValuePair(null, -Double.MAX_VALUE);
        ArrayList<Action> actions = game.getActions(state);

        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = minValeur(nextState, depth+1);

            if(pair.getValue() >= maxPair.getValue()){
                maxPair = new ActionValuePair(action, pair.getValue());
            }
        }

        return maxPair;
    }

    public ActionValuePair minValeur(GameState state, int depth) {
        incStateCounter();
        if(game.endOfGame(state) || depth>=this.maxDepth) {
            return new ActionValuePair(null, state.getGameValue());
        }
        ActionValuePair minPair = new ActionValuePair(null, Double.MAX_VALUE);
        ArrayList<Action> actions = game.getActions(state);

        for(Action action : actions){
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair pair = maxValeur(nextState, depth+1);

            if(pair.getValue() <= minPair.getValue()){
                minPair = new ActionValuePair(action, pair.getValue());
            }
        }

        return minPair;
    }
}
