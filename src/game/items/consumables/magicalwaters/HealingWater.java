package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * HealingWater, can be consumed and heal the consumer 50 HP
 */
public class HealingWater extends Water {

    /**
     * The constructor of HealingWater
     */
    public HealingWater() {
        super("Healing water", 'W', false);
    }

    /**
     * Heal the consumer by 50 HP
     *
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    @Override
    public void consumed(Actor actor, Location currentLocation) {
        actor.heal(50);
    }

    /**
     * @return The name of Healing Water
     */
    @Override
    public String toString() {
        return "Healing water";
    }
}
