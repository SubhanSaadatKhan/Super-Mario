package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing Fire
 */
public class Fire extends Item {
    private int value;  // turns that already exist

    /**
     * Constructor of Fire
     */
    public Fire() {
        super("Fire", 'v', false);
        value = 0;
    }

    /**
     * Fire extinguishes after 3 turns also gives 20 damage to the actor standing on it
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        value += 1;
        if (value % 4 == 0) {
            currentLocation.removeItem(this);
        } else {
            Actor ac = currentLocation.getActor();
            if (ac != null) {
                ac.hurt(20);
                new Display().println(ac + " standing on Fire got 20 damage");
            }
        }
    }
}
