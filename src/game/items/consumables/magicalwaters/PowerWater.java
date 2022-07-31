package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.actors.players.Player;

/**
 * PowerWater, can be consumed and make the consumer more powerful
 */
public class PowerWater extends Water {

    /**
     * The constructor of PowerWater
     */
    public PowerWater() {
        super("Power water", 'W', false);
    }

    /**
     * Increase the consumer's intrinsicWeaponDamage by 15
     *
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    @Override
    public void consumed(Actor actor, Location currentLocation) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
//            player.setIntrinsicWeaponDamage(player.getIntrinsicWeaponDamage() + 15);
        } else if (actor instanceof Enemy) {
            Enemy enemy = (Enemy) actor;
            enemy.setIntrinsicWeaponDamage(enemy.getIntrinsicWeaponDamage() + 15);
        }
    }

    /**
     * @return The name of Power Water
     */
    @Override
    public String toString() {
        return "Power water";
    }
}
