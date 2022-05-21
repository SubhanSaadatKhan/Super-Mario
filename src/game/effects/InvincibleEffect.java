package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import static game.Status.INVINCIBLE;
//final
/**
 * Invincible effect,
 * if it is in the player's inventory, the player will have the status INVINCIBLE
 */
public class InvincibleEffect extends Effect {
    /***
     * Constructor of InvincibleEffect,
     * it will initialize the InvincibleEffect.
     */
    public InvincibleEffect() {
        super("INVINCIBLE", ' ', false);
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
