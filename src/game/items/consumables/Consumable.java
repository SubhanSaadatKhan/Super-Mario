package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
//final
/**
 * This is an interface that created for consumable items.
 * Items that can be consumed will implement this interface
 */
public interface Consumable {
    /**
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    void consumed(Actor actor, Location currentLocation);
}
