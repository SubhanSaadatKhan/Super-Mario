package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;

import java.util.ArrayList;
import java.util.List;

public class PowerStar extends Item implements Consumable {
    private int turns;
    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', false);
        turns = 10;
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeItemAction(this));
        return actions;
    }

    @Override
    public void Consumed(Actor actor, Location currentLocation) {
        if (actor.getInventory().contains(this)) {
            actor.heal(200);
            actor.addItemToInventory(new InvincibleEffect("INVINCIBLE", ' ', false));
            actor.removeItemFromInventory(this);
        }
        else if (currentLocation.getItems().contains(this)) {
            actor.heal(200);
            actor.addItemToInventory(new InvincibleEffect("INVINCIBLE", ' ', false));
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        turns -= 1;
        if (turns == 0) {
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public void tick(Location currentLocation) {
        turns -= 1;
        if (turns == 0) {
            currentLocation.removeItem(this);
        }
    }
}
