package game.items.consumables;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.effects.FireAttackEffect;

import java.util.ArrayList;
import java.util.List;

public class FireFlower extends Item implements Consumable{
    /***
     * Constructor.
     */
    public FireFlower() {
        super("Fire Flower", 'f', false);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeItemAction(this));
        return actions;
    }

    @Override
    public void consumed(Actor actor, Location currentLocation) {
        actor.addItemToInventory(new FireAttackEffect());
        currentLocation.removeItem(this);
    }
}
