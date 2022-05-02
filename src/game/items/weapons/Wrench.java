package game.items.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

import static game.Status.DESTRUCTIVE;

public class Wrench extends WeaponItem {
    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'w', 50, "strike", 80);
        this.addCapability(DESTRUCTIVE);
    }
}
