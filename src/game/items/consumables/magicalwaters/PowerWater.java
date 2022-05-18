package game.items.consumables.magicalwaters;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.actors.players.Player;

public class PowerWater extends Water{

    public PowerWater() {
        super("Power water", 'W', false);
    }

    @Override
    public void consumed(Actor actor, Location currentLocation) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            player.setIntrinsicWeaponDamage(player.getIntrinsicWeaponDamage() + 5);
        }
        else if (actor instanceof Enemy) {
            Enemy enemy = (Enemy) actor;
            enemy.setIntrinsicWeaponDamage(enemy.getIntrinsicWeaponDamage() + 5);
        }
    }

    @Override
    public String toString() {
        return "Power water";
    }
}
