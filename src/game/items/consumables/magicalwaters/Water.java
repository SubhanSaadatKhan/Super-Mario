package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.items.Item;
import game.items.consumables.Consumable;

public abstract class Water extends Item implements Consumable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Water(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
