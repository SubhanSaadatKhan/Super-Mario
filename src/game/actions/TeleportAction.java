package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.jumpablegrounds.teleportablegrounds.TeleportManager;

public class TeleportAction extends Action {
    private Location currentLocation;
    private String direction;
    GameMap map1,map2;

    public TeleportAction(GameMap oldMap, GameMap newMap, Location location, String direction){
        map1 = oldMap;
        map2 = newMap;
        currentLocation = location;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.equals(map1)){
            TeleportManager.getInstance().setDestination(currentLocation);
            map2.moveActor(actor,map2.at(0,0));
        }
        else{
            Location loc = TeleportManager.getInstance().getDestination();
            map1.moveActor(actor,map1.at(loc.x(),loc.y()));
        }
        String output = actor + " teleported successfully " + direction + "!";
        return output;
    }

    @Override
    public String menuDescription(Actor actor) {

        return "Teleport " + direction;
    }
}
