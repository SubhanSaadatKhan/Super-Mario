package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;

import static game.Status.RESETTABLE;

/**
 * A class representing coin,
 * players can pick up coins on the map and trade with Toad using coins
 */
public class Coin extends Item implements Resettable {

    private int worth; // The value of the coin

    /**
     * Constructor of Coin,
     * initialize the coin with given parameters
     * used for the player's wallet
     *
     * @param worth    The value of the coin
     * @param portable if the coin is portable
     */
    public Coin(int worth, boolean portable) {
        super("Coin", '$', portable);
        this.worth = worth;
    }

    /**
     * Constructor of Coin,
     * initialize the coin with the given parameter
     *
     * @param worth The value of the coin
     */
    public Coin(int worth) {
        super("Coin", '$', true);
        this.worth = worth;
        this.registerInstance();
    }

    /**
     * If the coin is resettable, remove it from the map
     *
     * @param currentLocation The location of the ground on which the coin lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(RESETTABLE)) {
            currentLocation.removeItem(this);
            this.removeCapability(RESETTABLE);
        }
    }

    /**
     * Get the value of the coin
     *
     * @return The value of the coin
     */
    public int getWorth() {
        return worth;
    }

    /**
     * Increase the value of the coin with a given coin
     *
     * @param coin another coin represents the value
     */
    public void increase(Coin coin) {
        this.worth += coin.getWorth();
    }

    /**
     * Decrease the value of the coin with a given coin
     *
     * @param coin another coin represents the value
     * @return If the value of the coin can be decreased further
     */
    public boolean decrease(Coin coin) {
        if (this.worth - coin.getWorth() >= 0) {
            this.worth -= coin.getWorth();
            return true;
        }
        return false;
    }

    /**
     * @return the coin with its value
     */
    @Override
    public String toString() {
        return "coin:$" + worth;
    }

    /**
     * Make the Coin object resettable
     */
    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }

    /**
     * Create and return a PickUpCoinAction to pick the coin up.
     *
     * @param actor the actor who will pick up the coin
     * @return a new PickUpCoinAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }
}
