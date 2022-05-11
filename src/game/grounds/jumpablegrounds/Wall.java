package game.grounds.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.grounds.Dirt;
import game.items.Coin;

import static game.Status.INVINCIBLE;
import static game.Status.TALL;
//final
/**
 * Class representing Solid Wall.
 */
public class Wall extends Ground implements JumpableGround {

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
        if (actor.getDisplayChar() == 'm') { //player cannot enter wall without jumping
            return false;
        }
        return true;
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
            map.moveActor(act, at);
            return actor + " had a successfully jump at Wall(" + at.x() + "," + at.y() + ")!";
        } else {
            actor.hurt(20);
            return actor + " fails to jump the Wall, faced a 20 fall damage!";
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
        if (direction.equals("") == false){
            return new ActionList(new JumpAction(this, location, direction));
        }
        return super.allowableActions(actor,location,direction);
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
