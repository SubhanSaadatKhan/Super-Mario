package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Objects;

import static game.Status.INVINCIBLE;

public class InvincibleEffect extends Item {
    private int turns;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public InvincibleEffect(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addCapability(INVINCIBLE);
        turns = 10;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        turns -= 1;
        if (turns == 0) {
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvincibleEffect that = (InvincibleEffect) o;
        return this.getDisplayChar() == that.getDisplayChar();
    }
}
