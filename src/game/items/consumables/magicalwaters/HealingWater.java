package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class HealingWater extends Water{

    public HealingWater() {
        super("Healing water", 'W', false);
    }

    @Override
    public void consumed(Actor actor, Location currentLocation) {
        actor.heal(50);
    }

    @Override
    public String toString() {
        return "Healing water";
    }
}
