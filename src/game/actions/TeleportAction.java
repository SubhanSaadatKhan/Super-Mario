package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.harmless.jumpablegrounds.teleportable.TeleportManager;

/**
 * Special Action for Teleporting between maps
 */
public class TeleportAction extends Action {
    private final Location currentLocation;
    private final String direction;
    GameMap map1, map2;

    /**
     * Constructor
     *
     * @param oldMap    GameMap
     * @param newMap    LavaMap
     * @param location  Current location of player
     * @param direction which map to teleport
     */
    public TeleportAction(GameMap oldMap, GameMap newMap, Location location, String direction) {
        map1 = oldMap;
        map2 = newMap;
        currentLocation = location;
        this.direction = direction;
    }

    /**
     * Performs teleport action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.equals(map1)) { //if player currently on GameMap
            TeleportManager.getInstance().setDestination(currentLocation); //stores location to arrive at the exact position of the pipe in the first map
            map2.moveActor(actor, map2.at(0, 0)); //move to LavaMap position 0,0
        } else { //if player currently on LavaMap
            Location loc = TeleportManager.getInstance().getDestination(); //retrieves position of player GameMap
            map1.moveActor(actor, map1.at(loc.x(), loc.y())); //moves to GameMap to his original location
        }
        return actor + " teleported successfully " + direction + "!";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {

        return "Teleport " + direction;
    }
}
