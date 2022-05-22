package game.items.consumables.magicalitems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;

import static game.Status.TALL;
//final

/**
 * SuperMushroom, a magical item that can be consumed by player,
 * it will increase the consumer's MaxHp and provide TALL status for the consumer
 */
public class SuperMushroom extends Item implements Consumable {
    /***
     * Constructor for SuperMushroom.
     * Initialize the attributes that a SuperMushroom has
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', false);
    }

    /**
     * This method will make the player be able to interact with SuperMushroom
     *
     * @return A ConsumeItemAction that allows the player to consume the SuperMushroom
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeItemAction(this));
        return actions;
    }

    /**
     * This method will let the SuperMushroom be consumed,
     * the consumer will increase 50 MaxHp and get TALL status after execution
     *
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    @Override
    public void consumed(Actor actor, Location currentLocation) {
        if (actor.getInventory().contains(this)) {
            actor.increaseMaxHp(50);
            actor.addCapability(TALL);
            actor.removeItemFromInventory(this);
        } else if (currentLocation.getItems().contains(this)) {
            actor.increaseMaxHp(50);
            actor.addCapability(TALL);
            currentLocation.removeItem(this);
        }
    }

    /**
     * @return The name of SuperMushroom
     */
    @Override
    public String toString() {
        return "SuperMushroom";
    }
}
