package game.grounds.harmless.fountains;

import edu.monash.fit2099.engine.positions.Ground;
import game.items.consumables.magicalwaters.Water;

import java.util.ArrayList;

/**
 * Abstract class representing Fountain, magical waters are storing in it
 */
public abstract class Fountain extends Ground {

    private final int maxCapacity; // The max capacity of the Fountain
    private int current; // The current waters in the Fountain
    private String waterType; // The type of water stored in the Fountain
    private int turnsToReplenished; // The turns to replenish water
    private final ArrayList<Water> waters; // The waters

    /**
     * Constructor of Fountain.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        maxCapacity = 10;
        current = 0;
        turnsToReplenished = 0;
        waters = new ArrayList<>(getMaxCapacity());
    }

    /**
     * Accessor of maxCapacity
     *
     * @return the maxCapacity of the Fountain
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Accessor of current waters
     *
     * @return the current waters
     */
    public int getCurrent() {
        return current;
    }

    /**
     * Mutator of current waters
     *
     * @param current the new current waters
     */
    public void setCurrent(int current) {
        this.current = current;
    }

    /**
     * Accessor of waters
     *
     * @return the waters
     */
    public ArrayList<Water> getWaters() {
        return waters;
    }

    /**
     * Get magical water from the Fountain
     *
     * @return the next water
     */
    public Water getAWater() {
        if (!isEmpty()) {
            Water water = waters.get(getCurrent() - 1);
            waters.remove(getCurrent() - 1);
            setCurrent(getCurrent() - 1);
            return water;
        }
        return null;
    }

    /**
     * Mutator of type of water
     *
     * @param waterType the new type of water
     */
    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    /**
     * Accessor of type of water
     *
     * @return the type of water
     */
    public String getWaterType() {
        return waterType;
    }

    /**
     * Accessor of turns to replenish water
     *
     * @return the turns to replenish water
     */
    public int getTurnsToReplenished() {
        return turnsToReplenished;
    }

    /**
     * Mutator of turns to replenish water
     *
     * @param turnsToReplenished the new turns to replenish water
     */
    public void setTurnsToReplenished(int turnsToReplenished) {
        this.turnsToReplenished = turnsToReplenished;
    }

    /**
     * Check if the Fountain is empty
     *
     * @return true if the Fountain is empty, false otherwise
     */
    public boolean isEmpty() {
        return current == 0;
    }
}
