package game.grounds.harmless.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

/**
 * A Base Class for all high grounds
 */
abstract class HighGround extends Ground implements JumpableGround {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        char show = actor.getDisplayChar();
        //player cannot enter wall without jumping
        return show != 'm' && show != 'g' && show != 'K';
    }

    /**
     * Adds the jump action to the action list of player
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (!direction.equals("")) {
            return new ActionList(new JumpAction(this, location, direction));
        }
        return super.allowableActions(actor, location, direction);
    }

    /**
     * override this method to implement jump action functionality
     *
     * @param by indicate who jumps the jumpable ground
     * @param at to indicate where in the game map the jumpable ground is jumped
     * @param in represents the map where jump is taking place
     * @return
     */
    @Override
    public abstract String jumped(Actor by, Location at, GameMap in);
}
