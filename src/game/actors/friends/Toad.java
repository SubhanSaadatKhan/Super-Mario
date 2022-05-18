package game.actors.friends;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GetBottleAction;
import game.actions.TradingAction;
import game.items.portable.Coin;
import game.items.consumables.magicalitems.PowerStar;
import game.items.consumables.magicalitems.SuperMushroom;
import game.items.weapons.Wrench;
//final
/**
 * Toad, a friendly actor who can sell items to the player.
 */
public class Toad extends Friend {

    private boolean hasBottle;

    /**
     * Constructor of Toad,
     * initialize the Toad.
     */
    public Toad() {
        super("Toad", 'O', 1);
        hasBottle = true;
    }

    /**
     * Toad will do nothing.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return A DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions,lastAction,map,display);
    }

    /**
     * This method will make the player be able to interact with Toad
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A list of actions that players can do with the Toad
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new TradingAction(new Wrench(), new Coin(200)));
        actions.add(new TradingAction(new SuperMushroom(), new Coin(400)));
        actions.add(new TradingAction(new PowerStar(), new Coin(600)));
        if (hasBottle) {
            actions.add(new GetBottleAction(this));
        }
        return actions;
    }

    public void sendsBottle() {
        hasBottle = false;
    }
}
