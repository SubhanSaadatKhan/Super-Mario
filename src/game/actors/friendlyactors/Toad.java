package game.actors.friendlyactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradingAction;
import game.item.Coin;
import game.item.consumables.PowerStar;
import game.item.consumables.SuperMushroom;

public class Toad extends Actor {

    /**
     * Constructor.
     *
     */
    public Toad() {
        super("Toad", 'O', 1);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new TradingAction(new SuperMushroom(), new Coin(400)));
        actions.add(new TradingAction(new PowerStar(), new Coin(600)));
        return actions;
    }
}
