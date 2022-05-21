package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import static game.Status.FIRE_ATTACK;

/**
 * Effect that can make the player perform a FireAttack
 */
public class FireAttackEffect extends Effect {
    /***
     * Constructor of FireAttackEffect.
     */
    public FireAttackEffect() {
        super("FireAttackEffect", '_', false);
        this.addCapability(FIRE_ATTACK);
        turns = 21;
    }

    /**
     * Reduce 1 turn of FireAttackEffect
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
     * @return if the given object is this FireAttackEffect object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireAttackEffect that = (FireAttackEffect) o;
        return this.getDisplayChar() == that.getDisplayChar();
    }
}
