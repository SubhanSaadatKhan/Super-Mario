package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import static game.Status.FIRE_ATTACK;

public class FireAttackEffect extends Item {

    private int turns; // The number of turns that the FireAttackEffect can exist

    /***
     * Constructor.
     */
    public FireAttackEffect() {
        super("FireAttackEffect", '_', false);
        this.addCapability(FIRE_ATTACK);
        turns = 21;
    }

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
        FireAttackEffect that = (FireAttackEffect) o;
        return this.getDisplayChar() == that.getDisplayChar();
    }
}
