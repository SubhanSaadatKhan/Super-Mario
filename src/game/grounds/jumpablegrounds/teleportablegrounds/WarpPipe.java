package game.grounds.jumpablegrounds.teleportablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.grounds.jumpablegrounds.JumpableGround;
import game.grounds.jumpablegrounds.Wall;

import static game.Status.SPACE_SUIT;

public class WarpPipe extends Ground implements JumpableGround {
    boolean spawnPiranha;
    GameMap map1,map2;

    public WarpPipe(GameMap initDefaultMap,GameMap initNewMap){
        super('C');
        map1 = initDefaultMap;
        map2 = initNewMap;
        spawnPiranha = false;
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
    public void tick(Location location) {
        if (!spawnPiranha && !location.map().equals(map2)){
            location.addActor(new PiranhaPlant());
            spawnPiranha = true;
        }
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
        if ((actor.hasCapability(SPACE_SUIT))){
            if (!direction.equals("")){
                return new ActionList(new JumpAction(this, location, direction));
            }
            else {
                if (location.map().equals(map1)){
                    return new ActionList(new TeleportAction(map1,map2,location,"to Lava Map"));
                }
                return new ActionList(new TeleportAction(map1,map2,location,"to Game Map"));
            }
        }
        else{
            return super.allowableActions(actor,location,direction);
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
