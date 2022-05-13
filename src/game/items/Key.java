package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.PickUpCoinAction;

//import static game.Status.INTERACTWITHPRINCESS;
import static game.Status.INTERACT_WITH_PRINCESS;

public class Key extends Item {

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
        actor.addCapability(INTERACT_WITH_PRINCESS);
        return super.getPickUpAction(actor);
    }

    @Override
    public String toString() {
        return "Key";
    }

}
