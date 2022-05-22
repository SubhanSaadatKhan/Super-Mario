package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;


import static game.Status.RESETTABLE;
//final

/**
 * Class representing Goomba a little fungus guy.
 */
public class Goomba extends Enemy {

    /**
     * Constructor.
     */
    public Goomba() {
        super("Goomba", 'g', 20);
        this.behaviours.put(3, new WanderBehaviour());
        setIntrinsicWeaponDamage(10);
    }

    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {

        return new IntrinsicWeapon(getIntrinsicWeaponDamage(), "kick");
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
        // self-destruct Goomba
        if (Math.random() <= 0.1) {
            map.removeActor(this);
            return new DoNothingAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }

}
