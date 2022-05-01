package game.items.weapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;

import static game.Status.DESTRUCTIVE;

public class Wrench extends WeaponItem {
    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", ' ', 50, "strike", 80);
        this.addCapability(DESTRUCTIVE);
    }
}
