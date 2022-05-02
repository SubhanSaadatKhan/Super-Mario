package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.Behaviour;

import static game.Status.DORMANT;
import static game.Status.RESETTABLE;

/**
 * Class representing Koopa a little turtle.
 */
public class Koopa extends Enemy {

//    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
//        this.behaviours.put(0,new AttackBehaviour());
//        this.behaviours.put(2, new WanderBehaviour());
    }

    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }

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
        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public void dormant() {
        this.setDisplayChar('D');
        this.addCapability(DORMANT);
    }

    @Override
    public String toString() {
        if (this.hasCapability(DORMANT)) {
            return name + "'s shell";
        }
        return name;
    }
}
