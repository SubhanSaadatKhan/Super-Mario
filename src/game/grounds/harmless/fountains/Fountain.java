package game.grounds.harmless.fountains;

import edu.monash.fit2099.engine.positions.Ground;
import game.items.consumables.magicalwaters.Water;

import java.util.ArrayList;

public abstract class Fountain extends Ground {

    private final int maxCapacity;
    private int current;
    private String waterType;
    private int turnsToReplenished;
    private final ArrayList<Water> waters;

    /**
     * Constructor.
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public ArrayList<Water> getWaters() {
        return waters;
    }

    public Water getAWater() {
        if (!isEmpty()) {
            Water water = waters.get(getCurrent() - 1);
            waters.remove(getCurrent() - 1);
            setCurrent(getCurrent() - 1);
            return water;
        }
        return null;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getWaterType() {
        return waterType;
    }

    public int getTurnsToReplenished() {
        return turnsToReplenished;
    }

    public void setTurnsToReplenished(int turnsToReplenished) {
        this.turnsToReplenished = turnsToReplenished;
    }

    public boolean isEmpty() {
        return current == 0;
    }
}
