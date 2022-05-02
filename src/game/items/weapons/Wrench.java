package game.items.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

import static game.Status.DESTRUCTIVE;
//final
public class Wrench extends WeaponItem {
    /**
     * Constructor of Wrench,
     * initialize the Wrench
     */
    public Wrench() {
        super("Wrench", 'w', 50, "strike", 80);
        this.addCapability(DESTRUCTIVE);
    }
}
