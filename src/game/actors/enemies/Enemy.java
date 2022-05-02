package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

import java.util.Map;
import java.util.TreeMap;

import static game.Status.RESETTABLE;

/**
 * An abstract Class representing Enemy of the player.
 */
public abstract class Enemy extends Actor implements Resettable {

    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor
     *
     * @param name        Enemy name
     * @param displayChar Enemy char
     * @param hitPoints   Enemy HP
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(0, new AttackBehaviour());
        this.behaviours.put(2, new WanderBehaviour());
        this.registerInstance();
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
        this.behaviours.put(1, new FollowBehaviour(otherActor)); //attack action takes place thus follow behaviour implemented
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        actions.add(new AttackAction(this, direction));
        return actions;
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
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

    /**
     * Make the Enemy object resettable
     */
    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }
}
