package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;

import static game.Status.RESETTABLE;

/**
 * A class representing Coin
 */
public class Coin extends Item implements Resettable {
    private int worth;

    public Coin(int worth, boolean portable) {
        super("Coin", '$', portable);
        this.worth = worth;
    }

    public Coin(int worth) {
        super("Coin", '$', true);
        this.worth = worth;
        this.registerInstance();
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(RESETTABLE)) {
            currentLocation.removeItem(this);
            this.removeCapability(RESETTABLE);
        }
    }

    public int getWorth() {
        return worth;
    }

    public void increase(Coin coin) {
        this.worth += coin.getWorth();
    }

    @Override
    public String toString() {
        return "coin:$" + worth;
    }

    public boolean decrease(Coin coin) {
        if (this.worth - coin.getWorth() >= 0) {
            this.worth -= coin.getWorth();
            return true;
        }
        return false;
    }

    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }
}
