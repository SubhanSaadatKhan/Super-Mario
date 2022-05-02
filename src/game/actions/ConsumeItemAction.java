package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.Consumable;
//final
/**
 * This is ConsumeItemAction, it allows players to consume magical items
 */
public class ConsumeItemAction extends Action {

    protected Consumable target; // The item that will be consumed

    /**
     * The constructor of ConsumeItemAction,
     * initialize the ConsumeItemAction with given target
     *
     * @param target The item that will be consumed
     */
    public ConsumeItemAction(Consumable target) {
        this.target = target;
    }

    /**
     * Execute this ConsumeItemAction
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The description of the result after execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.Consumed(actor, map.locationOf(actor));
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
        return actor + " consumes " + target;
    }
}
