package game.item.consumables;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;

import java.util.ArrayList;
import java.util.List;

import static game.Status.TALL;

public class SuperMushroom extends Item implements Consumable {
    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', false);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeItemAction(this));
        return actions;
    }

    @Override
    public void Consumed(Actor actor, Location currentLocation) {
        if (actor.getInventory().contains(this)) {
            actor.increaseMaxHp(50);
            actor.addCapability(TALL);
            actor.removeItemFromInventory(this);
        }
        else if (currentLocation.getItems().contains(this)) {
            actor.increaseMaxHp(50);
            actor.addCapability(TALL);
            currentLocation.removeItem(this);
        }
    }
}
