package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface Consumable {
    void Consumed(Actor actor, Location currentLocation);
}
