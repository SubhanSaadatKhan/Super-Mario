package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.players.Player;
import game.items.portable.Coin;
import game.items.consumables.magicalitems.PowerStar;
//final

/**
 * Action for trading with Toad
 */
public class TradingAction extends Action {

    protected Item target; // The item that will be sold
    protected Coin price; // The coins needed for the item

    /**
     * The constructor of TradingAction,
     * initialize the TradingAction with given target and price
     *
     * @param target The item that will be sold
     * @param price  The coins needed for the item
     */
    public TradingAction(Item target, Coin price) {
        this.target = target;
        this.price = price;
    }

    /**
     * Execute this TradingAction
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The description of the result after execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getWallet().decrease(price)) {
            if (target instanceof PowerStar) {
                actor.addItemToInventory(new PowerStar(11));
            } else {
                actor.addItemToInventory(target);
            }
            return actor + " buys " + target;
        }
        return actor + " does not have enough money to buy " + target;
    }

    /**
     * Return the menu description of this action
     *
     * @param actor The actor performing the action.
     * @return The description of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys a " + target + " (" + price + ")";
    }
}
