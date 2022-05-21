package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.grounds.harmless.fountains.Fountain;
import game.items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The magical bottle class, player can use it to store magical water to drink
 */
public class Bottle extends Item implements Consumable {

    Stack<Water> bottle; // To store water

    /***
     * Constructor of Bottle.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        bottle = new Stack<>();
    }

    /**
     * This method will make the player be able to interact with Bottle
     *
     * @return the ConsumeItemAction if the Bottle is not empty
     */
    @Override
    public List<Action> getAllowableActions() {
        if (!bottle.isEmpty()) {
            List<Action> actions = new ArrayList<>();
            actions.add(new ConsumeItemAction(this));
            return actions;
        }
        return super.getAllowableActions();
    }

    /**
     * Refill the bottle using water in the fountain
     *
     * @param fountain the fountain that provide water to refill
     */
    public void refill(Fountain fountain) {
        if (!fountain.isEmpty()) {
            int slots = 5;
            for (int i = fountain.getCurrent(); i > 0 && slots > 0; i--, slots--) {
                bottle.push(fountain.getWaters().get(i - 1));
                fountain.getWaters().remove(i - 1);
                fountain.setCurrent(fountain.getCurrent() - 1);
            }
        }
    }

    /**
     * Consume the next water in the bottle
     *
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    @Override
    public void consumed(Actor actor, Location currentLocation) {
        bottle.pop().consumed(actor, currentLocation);
    }

    /**
     * @return The name of the Bottle and waters in it
     */
    @Override
    public String toString() {
        return "Bottle" + bottle;
    }
}
