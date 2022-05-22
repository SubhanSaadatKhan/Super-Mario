package game.grounds.harmless.jumpablegrounds.teleportablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.grounds.harmless.jumpablegrounds.JumpableGround;
import game.reset.Resettable;

import static game.Status.RESETTABLE;
import static game.Status.SPACE_SUIT;

/**
 * Class representing Warp Pipe
 */
public class WarpPipe extends Ground implements JumpableGround, Resettable {
    boolean spawnPiranha;
    GameMap map1, map2; //stores both maps

    /**
     * Constructor
     *
     * @param initDefaultMap
     * @param initNewMap
     */
    public WarpPipe(GameMap initDefaultMap, GameMap initNewMap) {
        super('C');
        map1 = initDefaultMap;
        map2 = initNewMap;
        spawnPiranha = false;
        this.registerInstance();
    }

    /**
     * Make the tree object resettable
     */
    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }


    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return true (if actor not player)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        char show = actor.getDisplayChar();
        //player cannot enter wall without jumping
        return show != 'm' && show != 'g' && show != 'K' && show != 'F';
    }

    /**
     * Spawn Piranha Plant
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (!spawnPiranha && !location.map().equals(map2)) {
            location.addActor(new PiranhaPlant());
            spawnPiranha = true;
        } else if (this.hasCapability(RESETTABLE)) {
            if (location.getActor() == null && !location.map().equals(map2)) {
                location.addActor(new PiranhaPlant());
            }
            this.removeCapability(RESETTABLE);
        }
    }

    /**
     * Makes the player jump onto Warp pipe
     *
     * @param by indicate who jumps the jumpable ground
     * @param at to indicate where in the game map the jumpable ground is jumped
     * @param in represents the map where jump is taking place
     * @return
     */
    @Override
    public String jumped(Actor by, Location at, GameMap in) {
        in.moveActor(by, at);
        return by + " had a successfully jump at Warp Pipe(" + at.x() + "," + at.y() + ")!";
    }

    /**
     * Adds the jump action and teleport action to the action list of player
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actionList of player
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if ((actor.hasCapability(SPACE_SUIT))) { //actions with warp pipe are only visible when player kills piranha plant
            if (!direction.equals("")) { //if player not standing upon warp pipe
                return new ActionList(new JumpAction(this, location, direction)); //jump action
            } else { //if player standing onto warp pipe
                if (location.map().equals(map1)) { //checks which map player currently on
                    return new ActionList(new TeleportAction(map1, map2, location, "to Lava Map")); //teleport action
                }
                return new ActionList(new TeleportAction(map1, map2, location, "to Game Map")); //teleport action
            }
        } else {
            return super.allowableActions(actor, location, direction);
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
