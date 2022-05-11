package game.grounds.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.grounds.jumpablegrounds.JumpableGround;
import game.grounds.jumpablegrounds.Wall;

public class WarpPipe extends Ground implements JumpableGround {

    public WarpPipe(){
        super('C');
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return true (if actor not player)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.getDisplayChar() == 'm') { //player cannot enter warp pipe without jumping
            return false;
        }
        return true;
    }

    @Override
    public String jumped(Actor by, Location at, GameMap in) {
        in.moveActor(by, at);
        return by + " had a successfully jump at Warp Pipe(" + at.x() + "," + at.y() + ")!";
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
        return "Warp Pipe";
    }


}
