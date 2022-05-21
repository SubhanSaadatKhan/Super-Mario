package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friends.Toad;
import game.actors.players.Player;
import game.items.consumables.magicalwaters.Bottle;

/**
 * Action for getting a magical bottle from Toad
 */
public class GetBottleAction extends Action {

    protected Toad toad; // The Toad who will send the Bottle
    protected Bottle bottle; // The Bottle that will be sent

    /**
     * Constructor of GetBottleAction
     *
     * @param toad The Toad who will send the Bottle
     */
    public GetBottleAction(Toad toad) {
        this.toad = toad;
        this.bottle = new Bottle();
    }

    /**
     * Execute this GetBottleAction
     *
     * @param actor The actor who will get the Bottle.
     * @param map   The map the actor is on.
     * @return The description of the result after execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.addItemToInventory(bottle);
        player.getABottle(bottle);
        toad.sendsBottle();
        return actor + "gets a magical bottle from " + toad;
    }

    /**
     * Return the menu description of this action
     *
     * @param actor The actor who will get the Bottle.
     * @return The description of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets a magical bottle from Toad ";
    }
}
