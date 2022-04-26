package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An abstract Class representing Enemy of the player.
 */
public abstract class Enemy extends Actor {

    /**
     * Constructor
     * @param name Enemy name
     * @param displayChar Enemy char
     * @param hitPoints Enemy HP
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name,displayChar,hitPoints);
    }

    /**
     * Adds the Attack action to the actionlist of player
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(this.getDisplayChar()!='D' && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) { //attack action will not be added if a dormant state Koopa is found
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     *
     * @return the Action to be performed
     */
    @Override
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);
}
