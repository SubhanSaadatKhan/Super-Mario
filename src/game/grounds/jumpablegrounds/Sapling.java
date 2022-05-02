package game.grounds.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;
import game.items.Coin;

import static game.Status.*;

/**
 * Class representing Sapling the second stage of a Tree.
 */
public class Sapling extends Tree {
    private int value;

    public Sapling() {
        super('t');
        value = 0;
    }

    /**
     * Sapling produces Coin and grow into Mature with the passage of time
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (this.hasCapability(RESETTABLE)) {
            if (Math.random() <= 0.5) {
                location.setGround(new Dirt());
            }
            this.removeCapability(RESETTABLE);
            return;
        }
        //grows into Mature after 10 turns
        value += 1;
        if (value % 10 == 0) {
            location.setGround(new Mature());
        }
        //produces $20 coin
        else {
            if (Math.random() <= 0.1) {
                location.addItem(new Coin(20));
            }
        }
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        return super.canActorEnter(actor);
    }

    /**
     * Implement the criteria and consequences of jumping a Sapling
     *
     * @param act indicate who jumps the jumpable ground
     * @param at  to indicate where in the game map the jumpable ground is jumped
     * @param map represents the map where jump is taking place
     * @return string indicating if player succeeds or fails while jumping
     */
    @Override
    public String jumped(Actor act, Location at, GameMap map) {
        Actor actor = act;
        if (actor.hasCapability(INVINCIBLE)) {
            map.moveActor(act, at); //moves actor on a successful jump
            at.setGround(new Dirt());
            at.addItem(new Coin(5));
            return actor + " had moved to (" + at.x() + "," + at.y() + ")!";
        }
        if (actor.hasCapability(TALL)) {
            map.moveActor(act, at); //moves actor on a successful jump
            return actor + " had a successfully jump at Sprout(" + at.x() + "," + at.y() + ")!";
        }
        if (Math.random() <= 0.8) {
            map.moveActor(act, at); //moves actor on a successful jump
            return actor + " had a successfully jump at Sapling(" + at.x() + "," + at.y() + ")!";

        } else {
            actor.hurt(20); //damages actor on an unsuccessful jump
            return actor + " fails to jump the Sapling, faced a 20 fall damage!";
        }
    }

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

    /**
     * To display the name of class
     *
     * @return String representing the class name
     */
    @Override
    public String toString() {
        return "Sapling";
    }
}
