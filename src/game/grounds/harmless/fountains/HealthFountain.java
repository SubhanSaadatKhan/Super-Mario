package game.grounds.harmless.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillBottomAction;
import game.actors.players.Player;
import game.items.consumables.magicalwaters.HealingWater;

public class HealthFountain extends Fountains{

    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H');
        setWaterType("Healing water");
        for (int i = 0; i < 10; i++) {
            getWaters().add(new HealingWater());
        }
        setCurrent(getMaxCapacity());
    }

    @Override
    public void tick(Location location) {
        if (isEmpty()) {
            setTurnsToReplenished(5);
            return;
        }
        if (getTurnsToReplenished() > 0) {
            getWaters().add(new HealingWater());
            getWaters().add(new HealingWater());
            setCurrent(getCurrent() + 2);
            setTurnsToReplenished(getTurnsToReplenished() - 1);
        }
    }

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

    @Override
    public String toString() {
        return "Health Fountain (" + getCurrent() + "/" + getMaxCapacity() + ")";
    }
}
