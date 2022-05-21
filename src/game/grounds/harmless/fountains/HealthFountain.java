package game.grounds.harmless.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillBottomAction;
import game.actors.players.Player;
import game.items.consumables.magicalwaters.HealingWater;

/**
 * HealthFountain that stores Healing Water
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor of HealthFountain.
     */
    public HealthFountain() {
        super('H');
        setWaterType("Healing water");
        for (int i = 0; i < 10; i++) {
            getWaters().add(new HealingWater());
        }
        setCurrent(getMaxCapacity());
    }

    /**
     * Replenish water if needed
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (getTurnsToReplenished() > 0) {
            getWaters().add(new HealingWater());
            getWaters().add(new HealingWater());
            setCurrent(getCurrent() + 2);
            setTurnsToReplenished(getTurnsToReplenished() - 1);
        }
        if (isEmpty()) {
            setTurnsToReplenished(5);
        }
    }

    /**
     * This method will make the player be able to interact with HealthFountain
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the actions allowed
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            if (player.hasBottle() && direction.equals("") && !isEmpty()) {
                return new ActionList(new RefillBottomAction(this));
            }
        }
        return super.allowableActions(actor, location, direction);
    }

    /**
     * @return the name of the fountain and its water
     */
    @Override
    public String toString() {
        return "Health Fountain (" + getCurrent() + "/" + getMaxCapacity() + ")";
    }
}
