package game.items.portable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

import static game.Status.UNLOCK_PRINCESS;

/**
 * A class representing Key
 */
public class Key extends Item {

    /**
     * Constructor
     */
    public Key() {
        super("Key", 'k', true);
    }

    /**
     * Create and return a PickUpCoinAction to pick the coin up.
     *
     * @param actor the actor who will pick up the coin
     * @return a new PickUpCoinAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        actor.addCapability(UNLOCK_PRINCESS);
        return super.getPickUpAction(actor);
    }

    /**
     * To display the name of item
     *
     * @return String representing the class name
     */
    @Override
    public String toString() {
        return "Key";
    }

}
