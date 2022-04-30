package game.item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;

import static game.Status.RESETTABLE;

/**
 * A class representing Coin
 */
public class Coin extends Item implements Resettable {
    private int worth;

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(RESETTABLE)) {
            currentLocation.removeItem(this);
            this.removeCapability(RESETTABLE);
        }
    }

    public Coin(int worth_$) {
        super("Coin", '$', true);
        this.worth = worth_$;
        this.registerInstance();
    }

    public int getWorth() {
        return worth;
    }

    public void increase(Coin coin) {
        this.worth += coin.getWorth();
    }

    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }
}
