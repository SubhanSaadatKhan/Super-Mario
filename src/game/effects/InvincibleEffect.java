package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import static game.Status.INVINCIBLE;

/**
 * Invincible effect,
 * if it is in the player's inventory, the player will have the status INVINCIBLE
 */
public class InvincibleEffect extends Item {

    private int turns; // The number of turns that the InvincibleEffect can exist

    /***
     * Constructor of InvincibleEffect,
     * it will initialize the InvincibleEffect.
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public InvincibleEffect(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addCapability(INVINCIBLE);
        turns = 11;
    }

    /**
     * Reduce 1 turn of InvincibleEffect
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
     * @param o The object that will be compared with
     * @return if the given object is this InvincibleEffect object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvincibleEffect that = (InvincibleEffect) o;
        return this.getDisplayChar() == that.getDisplayChar();
    }
}
