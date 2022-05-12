package game.grounds.jumpablegrounds.teleportablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.grounds.jumpablegrounds.JumpableGround;
import game.grounds.jumpablegrounds.Wall;

public class WarpPipe extends Ground implements JumpableGround {

    GameMap map1,map2;

    public WarpPipe(GameMap initDefaultMap,GameMap initNewMap){
        super('C');
        map1 = initDefaultMap;
        map2 = initNewMap;
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
        else{
            if (location.map().equals(map1)){
                return new ActionList(new TeleportAction(map1,map2,location,"to Lava Map"));
            }
            return new ActionList(new TeleportAction(map1,map2,location,"to Game Map"));

        }

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
