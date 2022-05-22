package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;

import static game.Status.RESETTABLE;

//BOWSER
/**
 * A class representing Bowser
 */
public class Bowser extends Enemy {

    /**
     * Constructor
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        setIntrinsicWeaponDamage(80);
    }

    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getIntrinsicWeaponDamage(), "punch");
    }

    /**
     * Adds the Attack action to the actionlist of player
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.behaviours.put(0, new FollowBehaviour(otherActor)); //attack action takes place thus follow behaviour implemented
        return super.allowableActions(otherActor, direction, map);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(RESETTABLE)) {
            map.removeActor(this);
            map.at(3, 4).addActor(this);
            this.heal(this.getMaxHp());
            this.removeCapability(RESETTABLE);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
