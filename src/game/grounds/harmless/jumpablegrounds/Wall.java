package game.grounds.harmless.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.harmless.Dirt;
import game.items.portable.Coin;

import static game.Status.INVINCIBLE;
import static game.Status.TALL;
//final

/**
 * Class representing Solid Wall.
 */
public class Wall extends HighGround {

    public Wall() {
        super('#');
    }

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

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }


    /**
     * Implement the criteria and consequences of jumping a Wall
     *
     * @param act indicate who jumps the jumpable ground
     * @param at  to indicate where in the game map the jumpable ground is jumped
     * @param map represents the map where jump is taking place
     * @return string indicating if player succeeds or fails while jumping
     */
    @Override
    public String jumped(Actor act, Location at, GameMap map) {
        if (act.hasCapability(INVINCIBLE)) {
            map.moveActor(act, at); //moves actor on a successful jump
            at.setGround(new Dirt());
            at.addItem(new Coin(5));
            return act + " had moved to (" + at.x() + "," + at.y() + ")!";
        }
        if (act.hasCapability(TALL)) {
            map.moveActor(act, at); //moves actor on a successful jump
            return act + " had a successfully jump at Sprout(" + at.x() + "," + at.y() + ")!";
        }
        if (Math.random() <= 0.8) {
            map.moveActor(act, at);
            return act + " had a successfully jump at Wall(" + at.x() + "," + at.y() + ")!";
        } else {
            act.hurt(20);
            return act + " fails to jump the Wall, faced a 20 fall damage!";
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
        return "Wall";
    }

}
