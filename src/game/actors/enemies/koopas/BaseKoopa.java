package game.actors.enemies.koopas;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actors.enemies.Enemy;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import static game.Status.DORMANT;
import static game.Status.RESETTABLE;

/**
 * A Base Class for Koopa
 */
public abstract class BaseKoopa extends Enemy {
    /**
     * Constructor.
     */
    public BaseKoopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(3, new WanderBehaviour());
    }

    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected abstract IntrinsicWeapon getIntrinsicWeapon();

    /**
     * Adds the Attack action to the actionlist of player
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
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
            this.removeCapability(RESETTABLE);
            return new DoNothingAction();
        }
        //A dormant state Koopa will do nothing.
        if (this.hasCapability(DORMANT)) {
            return new DoNothingAction();
        }
        return super.playTurn(actions,lastAction,map,display);
    }

    /**
     * Changes display char to D
     */
    public void dormant() {
        this.setDisplayChar('D');
        this.addCapability(DORMANT);
    }

    /**
     * String representation
     * @return
     */
    @Override
    public String toString() {
        if (this.hasCapability(DORMANT)) {
            return name + "'s shell";
        }
        return name;
    }
}
