package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.grounds.harmless.fountains.Fountains;
import game.items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bottle extends Item implements Consumable {

    Stack<Water> bottle;

    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        bottle = new Stack<>();
    }

    @Override
    public List<Action> getAllowableActions() {
        if (!bottle.isEmpty()) {
            List<Action> actions = new ArrayList<>();
            actions.add(new ConsumeItemAction(this));
            return actions;
        }
        return super.getAllowableActions();
    }

    public void refill(Fountains fountains) {
        if (!fountains.isEmpty()) {
            int slots = 5;
            for (int i = fountains.getCurrent(); i > 0 && slots > 0; i--, slots--) {
                bottle.push(fountains.getWaters().get(i - 1));
                fountains.getWaters().remove(i - 1);
                fountains.setCurrent(fountains.getCurrent() - 1);
            }
        }
    }

    @Override
    public void consumed(Actor actor, Location currentLocation) {
        bottle.pop().consumed(actor, currentLocation);
    }

    @Override
    public String toString() {
        return "Bottle" + bottle;
    }
}
