package game.items.consumables.magicalitems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.effects.InvincibleEffect;
import game.items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;
//final

/**
 * PowerStar, a magical item that can be consumed by player,
 * it will heal the consumer and provide INVINCIBLE status for the consumer
 */
public class PowerStar extends Item implements Consumable {

    private int turns; // The number of turns that the PowerStar can exist

    /***
     * Constructor of PowerStar,
     * initialize the attributes of PowerStar.
     */
    public PowerStar() {
        super("Power Star", '*', false);
        turns = 10;
    }

    /***
     * Constructor of PowerStar,
     * initialize the attributes of PowerStar with given turns.
     * It will be used for trading.
     *
     * @param turns The number of turns that the PowerStar can exist
     */
    public PowerStar(int turns) {
        super("Power Star", '*', false);
        this.turns = turns;
    }

    /**
     * This method will make the player be able to interact with PowerStar
     *
     * @return A ConsumeItemAction that allows the player to consume the PowerStar
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeItemAction(this));
        return actions;
    }

    /**
     * This method will let the PowerStar be consumed,
     * the consumer will heal 200 Hp and get INVINCIBLE status for 10 turns after execution
     *
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    @Override
    public void consumed(Actor actor, Location currentLocation) {
        if (actor.getInventory().contains(this)) {
            actor.heal(200);
            actor.addItemToInventory(new InvincibleEffect());
            actor.removeItemFromInventory(this);
        } else if (currentLocation.getItems().contains(this)) {
            actor.heal(200);
            actor.addItemToInventory(new InvincibleEffect());
            currentLocation.removeItem(this);
        }
    }

    /**
     * Reduce 1 turn of the PowerStar,
     * it will be used when the PowerStar is in the inventory
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        turns -= 1;
        if (turns == 0) {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Reduce 1 turn of the PowerStar,
     * it will be used when the PowerStar is lying on a ground
     *
     * @param currentLocation The location of the ground on which the PowerStar lie.
     */
    @Override
    public void tick(Location currentLocation) {
        turns -= 1;
        if (turns == 0) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * @return The name and turns of PowerStar
     */
    @Override
    public String toString() {
        return "PowerStar(" + turns + "turns" + ')';
    }
}
