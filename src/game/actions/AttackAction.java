package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.enemies.koopas.BaseKoopa;
import game.actors.enemies.koopas.FlyingKoopa;
import game.actors.enemies.koopas.NormalKoopa;
import game.items.portable.Key;
import game.items.consumables.magicalitems.SuperMushroom;

import static game.Status.*;

//final

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();
        String result;
        if (actor.hasCapability(INVINCIBLE)) {
            if (target instanceof NormalKoopa) {
                map.locationOf(target).addItem(new SuperMushroom());
            }
            map.removeActor(target);
            result = System.lineSeparator() + target + " is killed.";
            return result;
        }

        if (target.getDisplayChar() == 'D' && target.hasCapability(DORMANT)) {
            if (actor.hasCapability(DESTRUCTIVE)) {
                if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                    return actor + " misses " + target + ".";
                }
                map.locationOf(target).addItem(new SuperMushroom());
                map.removeActor(target);
                actor.removeItemFromInventory((Item) weapon);
                result = System.lineSeparator() + "Koopa is killed.";
                return result;
            }
            return actor + " attacks Koopa's shell, nothing happens (Wrench is needed to destroy Koopa's shell.)";
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        if (target.hasCapability(INVINCIBLE)) {
            damage = 0;
        }

        result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);

            if (target.getDisplayChar() == 'K') { //do not remove an unconscious Koopa as it will go into dormant state
                BaseKoopa koopa = (NormalKoopa) target;
                koopa.dormant();
            } else if (target.getDisplayChar() == 'F') { //do not remove an unconscious Koopa as it will go into dormant state
                BaseKoopa flyingKoopa = (FlyingKoopa) target;
                flyingKoopa.dormant();
            } else if (target.getDisplayChar() == 'B') { //do not remove an unconscious Koopa as it will go into dormant state
                map.locationOf(target).addItem(new Key());
                // remove actor
                map.removeActor(target);
                result += System.lineSeparator() + target + " is killed, Key dropped";
            } else if (target.getDisplayChar() == 'Y') { //do not remove an unconscious Koopa as it will go into dormant state
                actor.addCapability(SPACE_SUIT);
                // remove actor
                map.removeActor(target);
                result += System.lineSeparator() + target + " is killed.";
            } else {
                // remove actor
                map.removeActor(target);
                result += System.lineSeparator() + target + " is killed.";
            }

        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}
