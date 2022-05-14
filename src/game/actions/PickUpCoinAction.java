package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.players.Player;
import game.items.portable.Coin;
//final
/**
 * Action for picking up coins, and put coins into wallet
 */
public class PickUpCoinAction extends PickUpItemAction {

    private final Coin coin; // The coin that will be picked up

    /**
     * Constructor of PickUpCoinAction,
     * initialize the coin that will be picked up
     *
     * @param coin the coin to pick up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    /**
     * Execute this PickUpCoinAction, add the coin to the player's wallet
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The description of the result after execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Player player = (Player) actor;
        player.getWallet().increase(coin);
        return menuDescription(actor);
    }

    /**
     * Return the menu description of this action
     *
     * @param actor The actor performing the action.
     * @return The description of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + coin;
    }
}
