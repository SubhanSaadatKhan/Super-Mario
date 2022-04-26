package game;

import edu.monash.fit2099.engine.items.Item;

/**
 * A class representing Coin
 */
public class Coin extends Item {
    private int worth;

    public Coin(int worth_$) {
        super("Coin", '$', true);
        this.worth = worth_$;
    }
}
