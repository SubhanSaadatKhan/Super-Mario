package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeItemAction;
import game.grounds.harmless.fountains.Fountain;
import game.items.consumables.magicalwaters.Water;

import java.util.ArrayList;

/**
 * The behaviour for NPCs to drink magical water
 */
public class DrinkWaterBehaviour implements Behaviour {

    /**
     * @param actor the Actor acting DrinkWaterBehaviour
     * @param map   the GameMap containing the Actor
     * @return the ConsumeItemAction witch the target is the water or no action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround() instanceof Fountain) {
            Fountain fountains = (Fountain) map.locationOf(actor).getGround();
            ArrayList<Water> waters = fountains.getWaters();
            if (!waters.isEmpty()) {
                return new ConsumeItemAction(fountains.getAWater());
            }
        }
        return null;
    }
}
