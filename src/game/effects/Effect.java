package game.effects;

import edu.monash.fit2099.engine.items.Item;

public abstract class Effect extends Item {

    protected int turns; // The number of turns that the Effect can exist

    /***
     * Constructor of Effect.
     *
     * @param name the name of this Effect
     * @param displayChar the character to use to represent this Effect if it is on the ground
     * @param portable true if and only if the Effect can be picked up
     */
    public Effect(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
