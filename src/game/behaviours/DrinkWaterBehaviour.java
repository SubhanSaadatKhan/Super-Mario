package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeItemAction;
import game.grounds.harmless.fountains.Fountains;
import game.items.consumables.magicalwaters.Water;

import java.util.ArrayList;

public class DrinkWaterBehaviour implements Behaviour{

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround() instanceof Fountains) {
            Fountains fountains = (Fountains) map.locationOf(actor).getGround();
            ArrayList<Water> waters =  fountains.getWaters();
            if (!waters.isEmpty()) {
                return new ConsumeItemAction(fountains.getAWater());
            }
        }
        return null;
    }
}
