package game.grounds.harmless.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;

import static game.Status.RESETTABLE;

abstract class Tree extends HighGround implements Resettable {
    /**
     * Constructor
     *
     * @param treeStage
     */
    public Tree(char treeStage) {
        super(treeStage);
        this.registerInstance();
    }

    /**
     * Different stages of Tree can experience change with the passage of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public abstract void tick(Location location);

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return true (if actor not player)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return super.canActorEnter(actor);
    }

    /**
     * Make the tree object resettable
     */
    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
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

    /**
     * Adds the jump action to the action list of player
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actionList of player
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return super.allowableActions(actor, location, direction);
    }
}

