package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class AttackBehaviour implements Behaviour {

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location enemyLoc = map.locationOf(actor);
        for (Exit exit : enemyLoc.getExits()) {
            Location destination = exit.getDestination();
            String direction = exit.getName();
            Actor actorPlayer = destination.getActor();
            if(actorPlayer!=null){
                char ch = actorPlayer.getDisplayChar();
                if (ch == 'm') {
                    return new AttackAction(actorPlayer, direction);
                }
            }


        }
        return null;
    }
}
